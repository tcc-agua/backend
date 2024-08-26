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
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

            // Agrupando os dados por ponto
            Map<Ponto, List<Coleta>> dadosPorPonto = pontos.stream()
                    .collect(Collectors.toMap(
                            ponto -> ponto,
                            ponto -> coletas.stream()
                                    .filter(coleta -> coleta.getPbSet().stream()
                                            .anyMatch(pb -> pb.getPonto().equals(ponto))
                                            || coleta.getCdSet().stream()
                                            .anyMatch(cd -> cd.getPonto().equals(ponto)))
                                    .collect(Collectors.toList())));

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
            for (Ponto ponto : pontos) {
                int numCols = ponto.getNome().contains("PB") ? 5 : 3;
                Cell pontoCell = headerRow.createCell(cellIndex);
                pontoCell.setCellValue(ponto.getNome());
                sheet.setColumnWidth(cellIndex, 6000); // Ajusta a largura da coluna

                // Mescla as células do cabeçalho do ponto
                sheet.addMergedRegion(new CellRangeAddress(0, 0, cellIndex, cellIndex + numCols - 1));
                cellIndex += numCols; // Incrementa o índice da célula para o próximo ponto
            }

            // Adiciona a linha de subcabeçalhos para os dados dos pontos
            Row subHeaderRow = sheet.createRow(rowNum++);
            cellIndex = 0;

            // Adiciona os subcabeçalhos gerais
            subHeaderRow.createCell(cellIndex++).setCellValue(""); // Espaço reservado para "Técnico"
            subHeaderRow.createCell(cellIndex++).setCellValue(""); // Espaço reservado para "Data"
            subHeaderRow.createCell(cellIndex++).setCellValue(""); // Espaço reservado para "Hora Início"
            subHeaderRow.createCell(cellIndex++).setCellValue(""); // Espaço reservado para "Hora Fim"

            // Adiciona os subcabeçalhos dos pontos
            for (Ponto ponto : pontos) {
                String[] headers = ponto.getNome().contains("PB") ?
                        new String[]{"Pressão PI-B.01.1 (kgf/cm²)", "Pulsos PQ-B.01.1", "Nível de Óleo (m)", "Nível d'água (m)", "Volume Manual Removido de Óleo (L)"} :
                        new String[]{"Pressão (Kgf/cm²)", "Hidrômetro (m³)", "Rede Pluvial ou ETAS?"};

                for (String header : headers) {
                    Cell headerCell = subHeaderRow.createCell(cellIndex++);
                    headerCell.setCellValue(header);
                }
            }

            // Mescla as células dos campos gerais nas duas linhas (cabeçalho e subcabeçalho)
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 0, 0)); // "Técnico"
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 1, 1)); // "Data"
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 2, 2)); // "Hora Início"
            sheet.addMergedRegion(new CellRangeAddress(0, 1, 3, 3)); // "Hora Fim"

            // Adiciona os dados de coleta
            for (Coleta coleta : coletas) {
                Row row = sheet.createRow(rowNum++);
                cellIndex = 0;

                // Adiciona os dados principais da coleta
                row.createCell(cellIndex++).setCellValue(coleta.getTecnico());
                row.createCell(cellIndex++).setCellValue(coleta.getDataColeta().toString());
                row.createCell(cellIndex++).setCellValue(coleta.getHora_inicio().toString());
                row.createCell(cellIndex++).setCellValue(coleta.getHora_fim().toString());

                // Adiciona os dados dos pontos
                for (Ponto ponto : pontos) {
                    PBs pb = coleta.getPbSet().stream().filter(pbItem -> pbItem.getPonto().equals(ponto)).findFirst().orElse(null);
                    CD cd = coleta.getCdSet().stream().filter(cdItem -> cdItem.getPonto().equals(ponto)).findFirst().orElse(null);

                    if (ponto.getNome().contains("PB") && pb != null) {
                        row.createCell(cellIndex++).setCellValue(pb.getPressao() != null ? pb.getPressao() : 0);
                        row.createCell(cellIndex++).setCellValue(pb.getPulsos() != null ? pb.getPulsos() : 0);
                        row.createCell(cellIndex++).setCellValue(pb.getNivel_oleo() != null ? pb.getNivel_oleo() : 0);
                        row.createCell(cellIndex++).setCellValue(pb.getNivel_agua() != null ? pb.getNivel_agua() : 0);
                        row.createCell(cellIndex++).setCellValue(pb.getVol_rem_oleo() != null ? pb.getVol_rem_oleo() : 0);
                    } else if (ponto.getNome().contains("CD") && cd != null) {
                        row.createCell(cellIndex++).setCellValue(cd.getPressao() != null ? cd.getPressao() : 0);
                        row.createCell(cellIndex++).setCellValue(cd.getHidrometro() != null ? cd.getHidrometro() : 0);
                        row.createCell(cellIndex++).setCellValue(cd.getTipo_rede() != null ? cd.getTipo_rede() : "Não especificado");
                    } else {
                        cellIndex += ponto.getNome().contains("PB") ? 5 : 3; // Avança o índice se não houver dados correspondentes
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

    // Método para criar o estilo dos cabeçalhos das colunas
    private CellStyle createHeaderStyle(XSSFWorkbook workbook) {
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        return headerStyle;
    }
}
