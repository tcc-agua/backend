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
            List<Excel> excels = excelRepository.findAll()
                    .stream()
                    .filter(excel -> Objects.equals(excel.getNome(), "CA"))
                    .toList();
            List<Coleta> coletasFiltradas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);

            for (Excel excel : excels) {
                if (!coletasFiltradas.isEmpty()) {
                    Sheet sheet = workbook.createSheet(excel.getNome());
                    int rowNum = 0;

                    // Criar cabeçalho
                    Row headerRow = sheet.createRow(rowNum++);
                    int cellIndex = 0;
                    headerRow.createCell(cellIndex++).setCellValue("Item");
                    headerRow.createCell(cellIndex++).setCellValue("Local do Hidrometro");

                    // Criar cabeçalhos dos meses
                    Set<String> allMeses = new TreeSet<>(); // Usando TreeSet para manter a ordem
                    Map<Hidrometro, Map<String, Double>> volumesPorHidrometro = new HashMap<>();

                    // Processar coletas e associar volumes
                    for (Coleta coleta : coletasFiltradas) {
                        String mesAno = coleta.getDataColeta().getMonthValue() + "/" + coleta.getDataColeta().getYear();
                        allMeses.add(mesAno); // Adiciona meses

                        for (Hidrometro hidrometro : coleta.getHidrometroSet()) {
                            // Inicializa o mapa para o hidrometro se não existir
                            volumesPorHidrometro.putIfAbsent(hidrometro, new HashMap<>());
                            Map<String, Double> volumesMes = volumesPorHidrometro.get(hidrometro);

                            // Armazena o volume diretamente, sem somar
                            volumesMes.put(mesAno, hidrometro.getVolume());
                        }
                    }

                    // Adicionar meses ao cabeçalho
                    cellIndex = 2; // Reiniciar cellIndex para a inserção dos meses
                    for (String mes : allMeses) {
                        headerRow.createCell(cellIndex++).setCellValue(mes);
                    }

                    // Adicionar dados das coletas sem repetição
                    Set<Long> idsHidrometros = new HashSet<>(); // Para evitar repetição de pontos
                    for (Hidrometro hidrometro : volumesPorHidrometro.keySet()) {
                        if (!idsHidrometros.contains(hidrometro.getPonto().getId())) {
                            Row dataRow = sheet.createRow(rowNum++);
                            cellIndex = 0;
                            dataRow.createCell(cellIndex++).setCellValue(hidrometro.getPonto().getId());
                            dataRow.createCell(cellIndex++).setCellValue(hidrometro.getPonto().getNome());

                            // Adicionar volumes por mês
                            for (String mes : allMeses) { // Usar allMeses para percorrer todos os meses
                                Double volume = volumesPorHidrometro.get(hidrometro).getOrDefault(mes, 0.0);
                                dataRow.createCell(cellIndex++).setCellValue(volume);
                                System.out.println("Hidrometro ID: " + hidrometro.getPonto().getId() + " - Mês: " + mes + " - VOLUME: " + volume);
                            }
                            idsHidrometros.add(hidrometro.getPonto().getId()); // Marca o hidrometro como processado
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
