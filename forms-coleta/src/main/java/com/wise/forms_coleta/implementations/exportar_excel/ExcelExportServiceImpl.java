package com.wise.forms_coleta.implementations.exportar_excel;

import com.wise.forms_coleta.entities.*;
import com.wise.forms_coleta.repositories.*;
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
import java.util.stream.Collectors;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Override
    public ByteArrayResource exportToExcel() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            List<Ponto> pontos = pontoRepository.findAll();
            List<Excel> excels = excelRepository.findAll();

            for (Excel excel : excels) {
                // Criar uma nova aba com o nome do Excel
                Sheet sheet = workbook.createSheet(excel.getNome());
                int rowNum = 0; // Reseta o número da linha para cada aba

                // Adiciona a linha de cabeçalhos principais
                Row headerRow = sheet.createRow(rowNum++);
                int cellIndex = 0;

                // Adiciona os cabeçalhos gerais
                headerRow.createCell(cellIndex++).setCellValue("Técnico");
                headerRow.createCell(cellIndex++).setCellValue("Data");
                headerRow.createCell(cellIndex++).setCellValue("Hora Início");
                headerRow.createCell(cellIndex++).setCellValue("Hora Fim");

                // Filtrar pontos pertencentes ao Excel atual
                List<Ponto> pontosFiltrados = pontos.stream()
                        .filter(ponto -> ponto.getExcel() != null && ponto.getExcel().getId().equals(excel.getId()))
                        .collect(Collectors.toList());

                // Adiciona os cabeçalhos dos pontos
                for (Ponto ponto : pontosFiltrados) {
                    int numCols = ponto.getNome().toUpperCase().contains("PB") ? 5 :
                            ponto.getNome().toUpperCase().contains("CD") ? 3 :
                                    (ponto.getNome().toUpperCase().contains("PMPT") || ponto.getNome().toUpperCase().contains("PT")) ? 3 :
                                            ponto.getNome().toUpperCase().contains("SENSOR") ? 1 :
                                                    ponto.getNome().toUpperCase().contains("FASE LIVRE") ? 2 :
                                                            ponto.getNome().toUpperCase().contains("TQ-01") ? 1 :
                                                                    ponto.getNome().toUpperCase().contains("BC-01") ? 5 :
                                                                            ponto.getNome().toUpperCase().contains("TQ-02") ? 2 : 0;

                    if (numCols > 0) {
                        Cell pontoCell = headerRow.createCell(cellIndex);
                        pontoCell.setCellValue(ponto.getNome());
                        sheet.setColumnWidth(cellIndex, 6000); // Ajusta a largura da coluna

                        // Mescla as células do cabeçalho do ponto, garantindo que o intervalo seja válido
                        if (numCols > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(0, 0, cellIndex, cellIndex + numCols - 1));
                        } else if (ponto.getNome().toUpperCase().contains("SENSOR")){
                            sheet.addMergedRegion(new CellRangeAddress(0,1, cellIndex, cellIndex));
                        } else if (ponto.getNome().toUpperCase().contains("TQ-01")){
                            sheet.addMergedRegion(new CellRangeAddress(0,1, cellIndex, cellIndex));
                        }
                        cellIndex += numCols; // Incrementa o índice da célula para o próximo ponto
                    }
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
                for (Ponto ponto : pontosFiltrados) {
                    String[] headers;

                    if (ponto.getNome().toUpperCase().contains("PB")) {
                        headers = new String[]{"Pressão PI-B.01.1 (kgf/cm²)", "Pulsos PQ-B.01.1", "Nível de Óleo (m)", "Nível d'água (m)", "Volume Manual Removido de Óleo (L)"};
                    } else if (ponto.getNome().toUpperCase().contains("CD")) {
                        headers = new String[]{"Pressão (Kgf/cm²)", "Hidrômetro (m³)", "Rede Pluvial ou ETAS?"};
                    } else if (ponto.getNome().toUpperCase().contains("PMPT") || ponto.getNome().toUpperCase().contains("PT")) {
                        headers = new String[]{"NA(m)", "NO(m)", "FL REMO. MANUAL (L)"};
                    } else if (ponto.getNome().toUpperCase().contains("SENSOR")) {
                        headers = new String[]{""};
                    } else if(ponto.getNome().toUpperCase().contains("FASE LIVRE")){
                        headers = new String[]{"Volume CT-01 (L)", "Houve Troca de Container?"};
                    } else if (ponto.getNome().toUpperCase().contains("TQ-01")){
                        headers = new String[]{""};
                    } else if (ponto.getNome().toUpperCase().contains("BC-01")){
                        headers = new String[]{"Horímetro (horas)", "Pressão PI-B.01.1 (bar)", "Frequência SV-B.01.1 (hz)", "Vazão FIT-B.01.1 (m³/h)", "Volume FIT-B.01.1 (m³/h)"};
                    } else if (ponto.getNome().toUpperCase().contains("TQ-02")){
                        headers = new String[]{"Sensor de pH PHIT-T.02.1", "LT-T.02.1 (%)"};
                    }
                    else {
                        headers = new String[]{};
                    }

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

                // Adiciona os dados de coleta filtrados pelos pontos da aba atual
                for (Coleta coleta : coletaRepository.findAll()) {
                    boolean coletaRelevante = coleta.getPbSet().stream().anyMatch(pb -> pontosFiltrados.contains(pb.getPonto())) ||
                            coleta.getCdSet().stream().anyMatch(cd -> pontosFiltrados.contains(cd.getPonto())) ||
                            coleta.getPmPtSet().stream().anyMatch(pmpt -> pontosFiltrados.contains(pmpt.getPonto())) ||
                            coleta.getPhSet().stream().anyMatch(sensorPh -> pontosFiltrados.contains(sensorPh.getPonto())) ||
                            coleta.getFaseLivreSet().stream().anyMatch(faseLivre -> pontosFiltrados.contains(faseLivre.getPonto())) ||
                            coleta.getTq01Set().stream().anyMatch(tq01 -> pontosFiltrados.contains(tq01.getPonto())) ||
                            coleta.getBC01Set().stream().anyMatch(bc01 -> pontosFiltrados.contains(bc01.getPonto())) ||
                            coleta.getTq02Set().stream().anyMatch(tq02 -> pontosFiltrados.contains(tq02.getPonto()));

                    if (coletaRelevante) {
                        Row row = sheet.createRow(rowNum++);
                        cellIndex = 0;

                        // Adiciona os dados principais da coleta
                        row.createCell(cellIndex++).setCellValue(coleta.getTecnico());
                        row.createCell(cellIndex++).setCellValue(coleta.getDataColeta().toString());
                        row.createCell(cellIndex++).setCellValue(coleta.getHora_inicio().toString());
                        row.createCell(cellIndex++).setCellValue(coleta.getHora_fim().toString());

                        // Adiciona os dados dos pontos
                        for (Ponto ponto : pontosFiltrados) {
                            PBs pb = coleta.getPbSet().stream()
                                    .filter(pbItem -> pbItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            CD cd = coleta.getCdSet().stream()
                                    .filter(cdItem -> cdItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            PmPt pmpt = coleta.getPmPtSet().stream()
                                    .filter(pmptItem -> pmptItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            SensorPH sensorPH = coleta.getPhSet().stream()
                                    .filter(sensorPhItem -> sensorPhItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            FaseLivre faseLivre = coleta.getFaseLivreSet().stream()
                                    .filter(faseLivreItem -> faseLivreItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            TQ01 tq01 = coleta.getTq01Set().stream()
                                    .filter(tq01Item -> tq01Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            BC01 bc01 = coleta.getBC01Set().stream()
                                    .filter(bc01Item -> bc01Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            TQ02 tq02 = coleta.getTq02Set().stream()
                                    .filter(tq02Item -> tq02Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);

                            if (ponto.getNome().toUpperCase().contains("PB") && pb != null) {
                                row.createCell(cellIndex++).setCellValue(pb.getPressao() != null ? pb.getPressao() : 0);
                                row.createCell(cellIndex++).setCellValue(pb.getPulsos() != null ? pb.getPulsos() : 0);
                                row.createCell(cellIndex++).setCellValue(pb.getNivel_oleo() != null ? pb.getNivel_oleo() : 0);
                                row.createCell(cellIndex++).setCellValue(pb.getNivel_agua() != null ? pb.getNivel_agua() : 0);
                                row.createCell(cellIndex++).setCellValue(pb.getVol_rem_oleo() != null ? pb.getVol_rem_oleo() : 0);
                            } else if (ponto.getNome().toUpperCase().contains("CD") && cd != null) {
                                row.createCell(cellIndex++).setCellValue(cd.getPressao() != null ? cd.getPressao() : 0);
                                row.createCell(cellIndex++).setCellValue(cd.getHidrometro() != null ? cd.getHidrometro() : 0);
                                row.createCell(cellIndex++).setCellValue(cd.getTipo_rede() != null ? cd.getTipo_rede() : "");
                            } else if ((ponto.getNome().toUpperCase().contains("PMPT") || ponto.getNome().toUpperCase().contains("PT")) && pmpt != null) {
                                row.createCell(cellIndex++).setCellValue(pmpt.getNivel_agua() != null ? pmpt.getNivel_agua() : 0);
                                row.createCell(cellIndex++).setCellValue(pmpt.getNivel_oleo() != null ? pmpt.getNivel_oleo() : 0);
                                row.createCell(cellIndex++).setCellValue(pmpt.getFl_remo_manual() != null ? pmpt.getFl_remo_manual() : 0);
                            } else if (ponto.getNome().toUpperCase().contains("SENSOR") && sensorPH != null) {
                                row.createCell(cellIndex++).setCellValue(sensorPH.getPh() != null ? sensorPH.getPh() : 0);
                            } else if (ponto.getNome().toUpperCase().contains("FASE LIVRE") && faseLivre != null) {
                                row.createCell(cellIndex++).setCellValue(faseLivre.getVolume() != null ? faseLivre.getVolume() : 0);
                                if(!faseLivre.getHouve_troca()){
                                    row.createCell(cellIndex++).setCellValue("NÃO");
                                } else {
                                    row.createCell(cellIndex++).setCellValue("SIM");
                                }
                            } else if(ponto.getNome().toUpperCase().contains("TQ-01") && tq01 != null){
                                row.createCell(cellIndex++).setCellValue(tq01.getNivel() != null ? tq01.getNivel() : "0");
                            } else if (ponto.getNome().toUpperCase().contains("BC-01") && bc01 != null){
                                row.createCell(cellIndex++).setCellValue(bc01.getHorimetro() != null ? bc01.getHorimetro() : 0);
                                row.createCell(cellIndex++).setCellValue(bc01.getPressao() != null ? bc01.getPressao() : 0);
                                row.createCell(cellIndex++).setCellValue(bc01.getFrequencia() != null ? bc01.getFrequencia() : 0);
                                row.createCell(cellIndex++).setCellValue(bc01.getVazao() != null ? bc01.getVazao() : 0);
                                row.createCell(cellIndex++).setCellValue(bc01.getVolume() != null ? bc01.getVolume() : 0);
                            } else if (ponto.getNome().toUpperCase().contains("TQ-02") && tq02 != null){
                                row.createCell(cellIndex++).setCellValue(tq02.getSensor_ph() != null ? tq02.getSensor_ph() : 0);
                                row.createCell(cellIndex++).setCellValue(tq02.getLt_02_1() != null ? tq02.getLt_02_1() : "0");
                            }
                        }
                    }
                }
            }

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                workbook.write(baos);
                return new ByteArrayResource(baos.toByteArray());
            }
        }
    }
}
