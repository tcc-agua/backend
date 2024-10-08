package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportHidrometroService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

@Service
public class ExcelExportHidrometroServiceImpl implements ExcelExportHidrometroService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ByteArrayResource exportToExcel(LocalDate startDate, LocalDate endDate) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            // Estilos
            CellStyle borderStyle = workbook.createCellStyle();
            borderStyle.setBorderBottom(BorderStyle.THIN);
            borderStyle.setBorderTop(BorderStyle.THIN);
            borderStyle.setBorderLeft(BorderStyle.THIN);
            borderStyle.setBorderRight(BorderStyle.THIN);

            CellStyle headerStyleRoyalBlue = workbook.createCellStyle();
            headerStyleRoyalBlue.setFillForegroundColor(IndexedColors.DARK_BLUE.getIndex());
            headerStyleRoyalBlue.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            headerStyleRoyalBlue.setBorderBottom(BorderStyle.MEDIUM);
            headerStyleRoyalBlue.setBorderTop(BorderStyle.MEDIUM);
            headerStyleRoyalBlue.setBorderLeft(BorderStyle.MEDIUM);
            headerStyleRoyalBlue.setBorderRight(BorderStyle.MEDIUM);
            headerStyleRoyalBlue.setAlignment(HorizontalAlignment.CENTER);
            headerStyleRoyalBlue.setVerticalAlignment(VerticalAlignment.CENTER);

            Font fontRoyalBlue = workbook.createFont();
            fontRoyalBlue.setColor(IndexedColors.WHITE.getIndex());
            fontRoyalBlue.setBold(true);
            headerStyleRoyalBlue.setFont(fontRoyalBlue);

            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            // Filtrando apenas os Excels relacionados ao nome "CA"
            List<Excel> excels = excelRepository.findAll()
                    .stream()
                    .filter(excel -> Objects.equals(excel.getNome(), "CA"))
                    .toList();

            // Filtrando as coletas pela data
            List<Coleta> coletasFiltradas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);

            for (Excel excel : excels) {
                if (!coletasFiltradas.isEmpty()) {
                    // Criação da aba de Hidrometros
                    Sheet sheetHidrometros = workbook.createSheet(excel.getNome());
                    int rowNum = 0;

                    // Criar cabeçalho
                    Row headerRow = sheetHidrometros.createRow(rowNum++);
                    int cellIndex = 0;
                    headerRow.createCell(cellIndex++).setCellValue("Item");
                    headerRow.createCell(cellIndex++).setCellValue("Local do Hidrometro");

                    // Aplicar estilo ao cabeçalho
                    for (int i = 0; i < cellIndex; i++) {
                        headerRow.getCell(i).setCellStyle(headerStyleRoyalBlue);
                    }

                    // Usando TreeSet para manter a ordem cronológica dos meses
                    Set<String> allMeses = new TreeSet<>();
                    Map<Long, Map<String, Double>> volumesPorHidrometro = new HashMap<>();

                    // Processar coletas e associar volumes
                    for (Coleta coleta : coletasFiltradas) {
                        String mesAno = coleta.getDataColeta().getMonthValue() + "/" + coleta.getDataColeta().getYear();
                        allMeses.add(mesAno); // Adiciona os meses ao cabeçalho

                        for (Hidrometro hidrometro : coleta.getHidrometroSet()) {
                            Long pontoId = hidrometro.getPonto().getId();

                            // Inicializa o mapa para o hidrômetro se não existir
                            volumesPorHidrometro.putIfAbsent(pontoId, new HashMap<>());
                            Map<String, Double> volumesMes = volumesPorHidrometro.get(pontoId);

                            // Soma os volumes para o mesmo mês
                            volumesMes.put(mesAno, volumesMes.getOrDefault(mesAno, 0.0) + hidrometro.getVolume());
                        }
                    }

                    // Adicionar meses ao cabeçalho
                    cellIndex = 2; // Reiniciar cellIndex para os meses
                    for (String mes : allMeses) {
                        Cell monthCell = headerRow.createCell(cellIndex++);
                        monthCell.setCellValue(mes);
                        monthCell.setCellStyle(headerStyleRoyalBlue); // Aplicar estilo aos meses
                    }

                    // Preencher dados das coletas
                    List<Long> pontoIds = new ArrayList<>(volumesPorHidrometro.keySet());
                    Collections.sort(pontoIds); // Ordenar IDs

                    for (Long pontoId : pontoIds) {
                        Row dataRow = sheetHidrometros.createRow(rowNum++);
                        cellIndex = 0;

                        // Preencher ponto e nome do hidrômetro usando o pontoId
                        List<Hidrometro> hidrometros = hidrometroRepository.findAllByPontoId(pontoId);
                        if (hidrometros != null && !hidrometros.isEmpty()) {
                            Hidrometro hidrometro = hidrometros.get(0); // Supondo que queremos o primeiro hidrômetro

                            dataRow.createCell(cellIndex++).setCellValue(pontoId);
                            dataRow.createCell(cellIndex++).setCellValue(hidrometro.getPonto().getNome());

                            // Aplicar estilo à célula
                            for (int i = 0; i < 2; i++) {
                                dataRow.getCell(i).setCellStyle(dataStyle);
                            }

                            // Preencher volumes por mês
                            for (String mes : allMeses) {
                                Double volume = volumesPorHidrometro.get(pontoId).getOrDefault(mes, 0.0);
                                if (volume == 0.0) {
                                    dataRow.createCell(cellIndex++).setCellValue("-");
                                } else {
                                    dataRow.createCell(cellIndex++).setCellValue(volume);
                                }
                                dataRow.getCell(cellIndex - 1).setCellStyle(dataStyle); // Aplicar estilo ao volume
                            }
                        }
                    }

                    // Nova aba para Consumo das Áreas
                    Sheet sheetConsumoAreas = workbook.createSheet("Consumo das Áreas");
                    rowNum = 0;

                    // Criar cabeçalho
                    Row headerRowConsumo = sheetConsumoAreas.createRow(rowNum++);
                    cellIndex = 0;
                    headerRowConsumo.createCell(cellIndex++).setCellValue("Item");
                    headerRowConsumo.createCell(cellIndex++).setCellValue("Local do Hidrometro");

                    // Aplicar estilo ao cabeçalho
                    for (int i = 0; i < cellIndex; i++) {
                        headerRowConsumo.getCell(i).setCellStyle(headerStyleRoyalBlue);
                    }

                    // Adicionar meses ao cabeçalho
                    cellIndex = 2;
                    for (String mes : allMeses) {
                        Cell monthCell = headerRowConsumo.createCell(cellIndex++);
                        monthCell.setCellValue(mes);
                        monthCell.setCellStyle(headerStyleRoyalBlue);
                    }

                    // Preencher dados da diferença entre meses
                    for (Long pontoId : pontoIds) {
                        Row dataRow = sheetConsumoAreas.createRow(rowNum++);
                        cellIndex = 0;

                        // Preencher ponto e nome do hidrômetro
                        List<Hidrometro> hidrometros = hidrometroRepository.findAllByPontoId(pontoId);
                        if (hidrometros != null && !hidrometros.isEmpty()) {
                            Hidrometro hidrometro = hidrometros.get(0);

                            dataRow.createCell(cellIndex++).setCellValue(pontoId);
                            dataRow.createCell(cellIndex++).setCellValue(hidrometro.getPonto().getNome());

                            // Aplicar estilo à célula
                            for (int i = 0; i < 2; i++) {
                                dataRow.getCell(i).setCellStyle(dataStyle);
                            }

                            // Calcular a diferença entre o mês atual e o anterior
                            String prevMes = null;
                            for (String mes : allMeses) {
                                Double volumeAtual = volumesPorHidrometro.get(pontoId).getOrDefault(mes, 0.0);
                                if (prevMes != null) {
                                    Double volumeAnterior = volumesPorHidrometro.get(pontoId).getOrDefault(prevMes, 0.0);
                                    Double diferenca = volumeAtual - volumeAnterior;
                                    if (diferenca == 0.0) {
                                        dataRow.createCell(cellIndex++).setCellValue("-");
                                    } else {
                                        dataRow.createCell(cellIndex++).setCellValue(diferenca);
                                    }
                                } else {
                                    dataRow.createCell(cellIndex++).setCellValue("-"); // Sem diferença para o primeiro mês
                                }
                                prevMes = mes;
                                dataRow.getCell(cellIndex - 1).setCellStyle(dataStyle); // Aplicar estilo à célula
                            }
                        }
                    }
                }
            }

            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayResource(outputStream.toByteArray());
        }
    }
}
