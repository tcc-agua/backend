package com.wise.forms_coleta.implementations.exportar_excel;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.PBs;
import com.wise.forms_coleta.entities.CD;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Override
    public ByteArrayResource exportToExcel() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            List<Coleta> coletas = coletaRepository.findAll();
            List<Ponto> pontos = pontoRepository.findAll();

            // Criando uma aba única para todos os pontos
            Sheet sheet = workbook.createSheet("Dados Coleta");
            int rowNum = 0; // Começa na primeira linha

            // Adiciona a linha de cabeçalhos principais
            Row headerRow = sheet.createRow(rowNum++);
            int cellIndex = 0;

            // Adiciona os cabeçalhos gerais
            headerRow.createCell(cellIndex++).setCellValue("Técnico");
            headerRow.createCell(cellIndex++).setCellValue("Data");
            headerRow.createCell(cellIndex++).setCellValue("Hora Início");
            headerRow.createCell(cellIndex++).setCellValue("Hora Fim");

            // Adiciona os cabeçalhos dos pontos
            Map<Ponto, Integer> pontoColumnMapping = new HashMap<>();
            int totalColumns = 4; // Contador de colunas para os cabeçalhos gerais
            for (Ponto ponto : pontos) {
                int numCols = ponto.getNome().contains("PB") ? 5 : 3;
                pontoColumnMapping.put(ponto, totalColumns);
                Cell pointHeaderCell = headerRow.createCell(totalColumns);
                pointHeaderCell.setCellValue(ponto.getNome());
                sheet.setColumnWidth(totalColumns, 6000); // Ajusta a largura da coluna

                // Mescla as células do cabeçalho do ponto
                sheet.addMergedRegion(new CellRangeAddress(0, 0, totalColumns, totalColumns + numCols - 1));
                totalColumns += numCols; // Incrementa o índice da célula para o próximo ponto
            }

            // Adiciona a linha de subcabeçalhos para os dados dos pontos
            Row subHeaderRow = sheet.createRow(rowNum++);
            int subHeaderIndex = 0;

            // Adiciona os subcabeçalhos gerais
            subHeaderRow.createCell(subHeaderIndex++); // Espaço reservado para "Técnico"
            subHeaderRow.createCell(subHeaderIndex++); // Espaço reservado para "Data"
            subHeaderRow.createCell(subHeaderIndex++); // Espaço reservado para "Hora Início"
            subHeaderRow.createCell(subHeaderIndex++); // Espaço reservado para "Hora Fim"

            // Adiciona os subcabeçalhos dos pontos
            for (Ponto ponto : pontos) {
                String[] headers = ponto.getNome().contains("PB") ?
                        new String[]{"Pressão PI-B.01.1 (kgf/cm²)", "Pulsos PQ-B.01.1", "Nível de Óleo (m)", "Nível d'água (m)", "Volume Manual Removido de Óleo (L)"} :
                        new String[]{"Pressão (Kgf/cm²)", "Hidrômetro (m³)", "Rede Pluvial ou ETAS?"};

                for (String header : headers) {
                    subHeaderRow.createCell(subHeaderIndex++).setCellValue(header);
                }
            }

            // Mescla as células dos campos gerais nas duas linhas (cabeçalho e subcabeçalho)
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0)); // "Técnico"
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1)); // "Data"
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2)); // "Hora Início"
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3)); // "Hora Fim"

            // Adiciona os dados de coleta
            for (Coleta coleta : coletas) {
                boolean hasData = false;

                // Verificação prévia dos dados antes de criar a linha
                for (Ponto ponto : pontos) {
                    PBs pb = coleta.getPbSet().stream().filter(pbItem -> pbItem.getPonto().equals(ponto)).findFirst().orElse(null);
                    CD cd = coleta.getCdSet().stream().filter(cdItem -> cdItem.getPonto().equals(ponto)).findFirst().orElse(null);

                    if ((ponto.getNome().contains("PB") && pb != null) || (ponto.getNome().contains("CD") && cd != null)) {
                        hasData = true; // Há dados para este ponto
                        break; // Se há dados, não precisamos continuar a verificação
                    }
                }

                // Se houver dados, cria a linha e preenche
                if (hasData) {
                    Row row = sheet.createRow(rowNum++);
                    cellIndex = 0;

                    // Adiciona os dados principais da coleta
                    row.createCell(cellIndex++).setCellValue(coleta.getTecnico());
                    row.createCell(cellIndex++).setCellValue(coleta.getDataColeta().toString());
                    row.createCell(cellIndex++).setCellValue(coleta.getHora_inicio().toString());
                    row.createCell(cellIndex++).setCellValue(coleta.getHora_fim().toString());

                    // Preenche os dados dos pontos
                    for (Ponto ponto : pontos) {
                        Integer startCol = pontoColumnMapping.get(ponto);
                        if (startCol == null) continue;

                        PBs pb = coleta.getPbSet().stream().filter(pbItem -> pbItem.getPonto().equals(ponto)).findFirst().orElse(null);
                        CD cd = coleta.getCdSet().stream().filter(cdItem -> cdItem.getPonto().equals(ponto)).findFirst().orElse(null);

                        if (ponto.getNome().contains("PB") && pb != null) {
                            row.createCell(startCol).setCellValue(pb.getPressao() != null ? pb.getPressao().toString() : "");
                            row.createCell(startCol + 1).setCellValue(pb.getPulsos() != null ? pb.getPulsos().toString() : "");
                            row.createCell(startCol + 2).setCellValue(pb.getNivel_oleo() != null ? pb.getNivel_oleo().toString() : "");
                            row.createCell(startCol + 3).setCellValue(pb.getNivel_agua() != null ? pb.getNivel_agua().toString() : "");
                            row.createCell(startCol + 4).setCellValue(pb.getVol_rem_oleo() != null ? pb.getVol_rem_oleo().toString() : "");
                        } else if (ponto.getNome().contains("CD") && cd != null) {
                            row.createCell(startCol).setCellValue(cd.getPressao() != null ? cd.getPressao().toString() : "");
                            row.createCell(startCol + 1).setCellValue(cd.getHidrometro() != null ? cd.getHidrometro().toString() : "");
                            row.createCell(startCol + 2).setCellValue(cd.getTipo_rede() != null ? cd.getTipo_rede() : "");
                        }
                    }
                }
            }

            // Escrever os dados do workbook no output stream e retornar como recurso
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                workbook.write(outputStream);
                return new ByteArrayResource(outputStream.toByteArray());
            }
        }
    }

    // Método para criar o estilo do cabeçalho dos pontos
    private CellStyle createPointHeaderStyle(XSSFWorkbook workbook) {
        CellStyle pointHeaderStyle = workbook.createCellStyle();
        pointHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        pointHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        pointHeaderStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        pointHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return pointHeaderStyle;
    }

    // Método para criar o estilo do subcabeçalho dos pontos
    private CellStyle createSubHeaderStyle(XSSFWorkbook workbook) {
        CellStyle subHeaderStyle = workbook.createCellStyle();
        subHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
        subHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        subHeaderStyle.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        subHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return subHeaderStyle;
    }
}
