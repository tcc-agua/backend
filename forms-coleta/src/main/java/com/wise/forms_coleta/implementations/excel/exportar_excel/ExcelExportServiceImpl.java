package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.*;
import com.wise.forms_coleta.repositories.*;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
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

    @Autowired
    private ExcelRepository excelRepository;

    private static final String DIRECTORY_PATH = "src/main/resources/excel";

    @Value("${excel.export.path}")
    private String exportPath;

    @Override
    public ByteArrayResource exportToExcel() throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {

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
            headerStyleRoyalBlue.setVerticalAlignment(VerticalAlignment.CENTER);

            Font fontRoyalBlue = workbook.createFont();
            fontRoyalBlue.setColor(IndexedColors.WHITE.getIndex()); // Define a cor da fonte para branco
            fontRoyalBlue.setBold(true);
            headerStyleRoyalBlue.setFont(fontRoyalBlue);

            Font fontSubHeader = workbook.createFont();
            fontSubHeader.setColor(IndexedColors.WHITE.getIndex()); // Define a cor da fonte para branco

            CellStyle subHeaderStyle = workbook.createCellStyle();
            subHeaderStyle.setFillForegroundColor(IndexedColors.ROYAL_BLUE.getIndex());
            subHeaderStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
            subHeaderStyle.setBorderBottom(BorderStyle.MEDIUM);
            subHeaderStyle.setBorderTop(BorderStyle.MEDIUM);
            subHeaderStyle.setBorderLeft(BorderStyle.MEDIUM);
            subHeaderStyle.setBorderRight(BorderStyle.MEDIUM);
            subHeaderStyle.setAlignment(HorizontalAlignment.CENTER);
            subHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);
            subHeaderStyle.setVerticalAlignment(VerticalAlignment.CENTER);

            subHeaderStyle.setFont(fontSubHeader);

            CellStyle dataStyle = workbook.createCellStyle();
            dataStyle.setBorderBottom(BorderStyle.THIN);
            dataStyle.setBorderTop(BorderStyle.THIN);
            dataStyle.setBorderLeft(BorderStyle.THIN);
            dataStyle.setBorderRight(BorderStyle.THIN);
            dataStyle.setAlignment(HorizontalAlignment.CENTER);
            dataStyle.setVerticalAlignment(VerticalAlignment.CENTER);

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
                Cell tecnicoHeader = headerRow.createCell(cellIndex++);
                tecnicoHeader.setCellValue("Técnico");
                tecnicoHeader.setCellStyle(headerStyleRoyalBlue);

                Cell dataHeader = headerRow.createCell(cellIndex++);
                dataHeader.setCellValue("Data");
                dataHeader.setCellStyle(headerStyleRoyalBlue);

                Cell horaInicioHeader = headerRow.createCell(cellIndex++);
                horaInicioHeader.setCellValue("Hora Início");
                horaInicioHeader.setCellStyle(headerStyleRoyalBlue);

                Cell horaFimHeader = headerRow.createCell(cellIndex++);
                horaFimHeader.setCellValue("Hora Fim");
                horaFimHeader.setCellStyle(headerStyleRoyalBlue);

                // Filtrar pontos pertencentes ao Excel atual
                List<Ponto> pontosFiltrados = pontos.stream()
                        .filter(ponto -> ponto.getExcel() != null && ponto.getExcel().getId().equals(excel.getId()))
                        .collect(Collectors.toList());

                // Adiciona os cabeçalhos dos pontos
                for (Ponto ponto : pontosFiltrados) {
                    int numCols = ponto.getNome().toUpperCase().contains("PB") ? 5 :
                            ponto.getNome().toUpperCase().contains("CD") ? 3 :
                                    (ponto.getNome().toUpperCase().contains("PM") || ponto.getNome().toUpperCase().contains("PT")) ? 3 :
                                            ponto.getNome().toUpperCase().contains("SENSOR") ? 1 :
                                                    ponto.getNome().toUpperCase().contains("FASE LIVRE") ? 2 :
                                                            ponto.getNome().toUpperCase().contains("TQ-01") ? 1 :
                                                                    ponto.getNome().toUpperCase().contains("BC-01") ? 5 :
                                                                            ponto.getNome().toUpperCase().contains("TQ-02") ? 2 :
                                                                                    ponto.getNome().toUpperCase().contains("BH-02") ? 3 :
                                                                                            ponto.getNome().toUpperCase().contains("FILTRO CARTUCHO") ? 2 :
                                                                                                    ponto.getNome().toUpperCase().contains("HORÍMETRO") ? 1 :
                                                                                                            ponto.getNome().toUpperCase().contains("COLUNAS DE CARVÃO") ? 6 :
                                                                                                                    ponto.getNome().toUpperCase().contains("BC-03") ? 3 :
                                                                                                                            (ponto.getNome().toUpperCase().contains("TQ-04") || ponto.getNome().toUpperCase().contains("TQ-05")) ? 5 :
                                                                                                                                    ponto.getNome().toUpperCase().contains("BC-06") ? 2 :
                                                                                                                                            ponto.getNome().toUpperCase().contains("BS-01 PRESSÃO") ? 1 :
                                                                                                                                                    ponto.getNome().toUpperCase().contains("BS-01 HIDRÔMETRO") ? 1 : 0;


                    if (numCols > 0) {
                        Cell pontoCell = headerRow.createCell(cellIndex);
                        pontoCell.setCellValue(ponto.getNome());
                        pontoCell.setCellStyle(headerStyleRoyalBlue);
                        sheet.autoSizeColumn(cellIndex); // Ajusta a largura da coluna

                        // Mescla as células do cabeçalho do ponto, garantindo que o intervalo seja válido
                        if (numCols > 1) {
                            sheet.addMergedRegion(new CellRangeAddress(0, 0, cellIndex, cellIndex + numCols - 1));
                        } else if (ponto.getNome().toUpperCase().contains("SENSOR")){
                            sheet.addMergedRegion(new CellRangeAddress(0,1, cellIndex, cellIndex));
                        } else if (ponto.getNome().toUpperCase().contains("TQ-01")){
                            sheet.addMergedRegion(new CellRangeAddress(0,1, cellIndex, cellIndex));
                        } else if (ponto.getNome().toUpperCase().contains("HORÍMETRO")){
                            sheet.addMergedRegion(new CellRangeAddress(0, 1, cellIndex, cellIndex));
                        } else if (ponto.getNome().toUpperCase().contains("BS-01 PRESSÃO")){
                            sheet.addMergedRegion(new CellRangeAddress(0, 1, cellIndex, cellIndex));
                        } else if (ponto.getNome().toUpperCase().contains("BS-01 HIDRÔMETRO")){
                            sheet.addMergedRegion(new CellRangeAddress(0, 1, cellIndex, cellIndex));
                        }
                        cellIndex += numCols; // Incrementa o índice da célula para o próximo ponto
                    }
                }

                // Adiciona a linha de subcabeçalhos para os dados dos pontos
                Row subHeaderRow = sheet.createRow(rowNum++);
                cellIndex = 0;

                // Adiciona os subcabeçalhos gerais
                Cell tecnicoSubHeader = subHeaderRow.createCell(cellIndex++);
                tecnicoSubHeader.setCellValue(""); // Espaço reservado para "Técnico"
                tecnicoSubHeader.setCellStyle(headerStyleRoyalBlue);

                Cell dataSubHeader = subHeaderRow.createCell(cellIndex++);
                dataSubHeader.setCellValue(""); // Espaço reservado para "Data"
                dataSubHeader.setCellStyle(headerStyleRoyalBlue);

                Cell horaInicioSubHeader = subHeaderRow.createCell(cellIndex++);
                horaInicioSubHeader.setCellValue(""); // Espaço reservado para "Hora Início"
                horaInicioSubHeader.setCellStyle(headerStyleRoyalBlue);

                Cell horaFimSubHeader = subHeaderRow.createCell(cellIndex++);
                horaFimSubHeader.setCellValue(""); // Espaço reservado para "Hora Fim"
                horaFimSubHeader.setCellStyle(headerStyleRoyalBlue);

                // Adiciona os subcabeçalhos dos pontos
                for (Ponto ponto : pontosFiltrados) {
                    String[] headers;

                    if (ponto.getNome().toUpperCase().contains("PB")) {
                        headers = new String[]{"Pressão PI-B.01.1 (kgf/cm²)", "Pulsos PQ-B.01.1", "Nível de Óleo (m)", "Nível d'água (m)", "Volume Manual Removido de Óleo (L)"};
                    } else if (ponto.getNome().toUpperCase().contains("CD")) {
                        headers = new String[]{"Pressão (Kgf/cm²)", "Hidrômetro (m³)", "Rede Pluvial ou ETAS?"};
                    } else if (ponto.getNome().toUpperCase().contains("PM") || ponto.getNome().toUpperCase().contains("PT")) {
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
                    } else if (ponto.getNome().toUpperCase().contains("BH-02")){
                        headers = new String[]{"Horímetro (horas)", "Pressão PI-B.02.1 (bar)", "Frequência SV-B.02.1 (hz)"};
                    } else if (ponto.getNome().toUpperCase().contains("FILTRO CARTUCHO")){
                        headers = new String[]{"Pressão de entrada PT-F.02.1", "Pressão de saída PT-F.02.1"};
                    } else if (ponto.getNome().toUpperCase().contains("HORÍMETRO")){
                        headers = new String[]{""};
                    } else if (ponto.getNome().toUpperCase().contains("COLUNAS DE CARVÃO")){
                        headers = new String[]{"Pressão C-01 PI-C.01.1 (bar)", "Pressão C-02 PI-C.02.1 (bar)", "Pressão C-03 PI-C.03.1 (bar)", "Pressão Saída Sistema PI-C.03.2 (bar)","Houve Troca de Carvão?", "Houve retrolavagem"};
                    } else if (ponto.getNome().toUpperCase().contains("BC-03")){
                        headers = new String[]{"Pressão PI-B.03.1 (bar)", "Hidrômetro FQ-B.03.1 (m³)", "Horímetro (horas)"};
                    } else if (ponto.getNome().toUpperCase().contains("TQ-04")){
                        headers = new String[]{"Houve Preparo de solução de ácido?", "Quantas bombonas?", "Bombonas de quantos kg?", "Horímetro BD-04 (horas)", "Hidrômetro FQ-T.04.1 (m³)"};
                    } else if (ponto.getNome().toUpperCase().contains("TQ-05")){
                        headers = new String[]{"Houve Preparo de solução de ácido?", "Quantas bombonas?", "Bombonas de quantos kg?", "Horímetro BD-05 (horas)", "Hidrômetro FQ-T.05.1 (m³)"};
                    } else if (ponto.getNome().toUpperCase().contains("BC-06")){
                        headers = new String[]{"Pressão PI-B.06.1 (bar)", "Horímetro BC-06 (horas)"};
                    } else if(ponto.getNome().toUpperCase().contains("BS-01 PRESSÃO")){
                        headers = new String[]{""};
                    } else if(ponto.getNome().toUpperCase().contains("BS-01 HIDRÔMETRO")){
                        headers = new String[]{""};
                    }
                    else {
                        headers = new String[]{};
                    }

                    for (String header : headers) {
                        Cell headerCell = subHeaderRow.createCell(cellIndex++);;
                        headerCell.setCellValue(header);
                        headerCell.setCellStyle(subHeaderStyle);
                    }

                    for (int i = 0; i < headers.length; i++) {
                        sheet.autoSizeColumn(cellIndex - headers.length + i);
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
                            coleta.getTq02Set().stream().anyMatch(tq02 -> pontosFiltrados.contains(tq02.getPonto())) ||
                            coleta.getBh02Set().stream().anyMatch(bh02 -> pontosFiltrados.contains(bh02.getPonto())) ||
                            coleta.getFiltroCartuchoSet().stream().anyMatch(filtroCartucho -> pontosFiltrados.contains(filtroCartucho.getPonto())) ||
                            coleta.getHorimetroSet().stream().anyMatch(horimetro -> pontosFiltrados.contains(horimetro.getPonto())) ||
                            coleta.getColunasCarvaoSet().stream().anyMatch(colunasCarvao -> pontosFiltrados.contains(colunasCarvao.getPonto())) ||
                            coleta.getBombaBc03Set().stream().anyMatch(bombaBc03 -> pontosFiltrados.contains(bombaBc03.getPonto())) ||
                            coleta.getTq04Tq05Set().stream().anyMatch(tq04Tq05 -> pontosFiltrados.contains(tq04Tq05.getPonto())) ||
                            coleta.getBc06Set().stream().anyMatch(bc06 -> pontosFiltrados.contains(bc06.getPonto())) ||
                            coleta.getBs01PressaoSet().stream().anyMatch(bs01Pressao -> pontosFiltrados.contains(bs01Pressao.getPonto())) ||
                            coleta.getBs01HidrometroSet().stream().anyMatch(bs01Hidrometro -> pontosFiltrados.contains(bs01Hidrometro.getPonto()));


                    if (coletaRelevante) {
                        Row row = sheet.createRow(rowNum++);
                        cellIndex = 0;

                        // Adiciona os dados principais da coleta
                        Cell dadosTecnico = row.createCell(cellIndex++);
                        dadosTecnico.setCellValue(coleta.getTecnico());
                        dadosTecnico.setCellStyle(borderStyle);

                        Cell dadosData = row.createCell(cellIndex++);
                        dadosData.setCellValue(coleta.getDataColeta().toString());
                        dadosData.setCellStyle(borderStyle);

                        Cell dadosHoraInicio = row.createCell(cellIndex++);
                        dadosHoraInicio.setCellValue(coleta.getHora_inicio().toString());
                        dadosHoraInicio.setCellStyle(borderStyle);

                        Cell dadosHoraFim = row.createCell(cellIndex++);
                        dadosHoraFim.setCellValue(coleta.getHora_fim().toString());
                        dadosHoraFim.setCellStyle(borderStyle);

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
                            BH02 bh02 = coleta.getBh02Set().stream()
                                    .filter(bh02Item -> bh02Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            FiltroCartucho filtroCartucho = coleta.getFiltroCartuchoSet().stream()
                                    .filter(filtroCartuchoItem -> filtroCartuchoItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            Horimetro horimetro = coleta.getHorimetroSet().stream()
                                    .filter(horimetroItem -> horimetroItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            ColunasCarvao colunasCarvao = coleta.getColunasCarvaoSet().stream()
                                    .filter(colunasCarvaoItem -> colunasCarvaoItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            BombaBc03 bombaBc03 = coleta.getBombaBc03Set().stream()
                                    .filter(bombaBc03Item -> bombaBc03Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            Tq04Tq05 tq04Tq05 = coleta.getTq04Tq05Set().stream()
                                    .filter(tq04Tq05Item -> tq04Tq05Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            BC06 bc06 = coleta.getBc06Set().stream()
                                    .filter(bc06Item -> bc06Item.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            BS01Pressao bs01Pressao = coleta.getBs01PressaoSet().stream()
                                    .filter(bs01PressaoItem -> bs01PressaoItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);
                            BS01Hidrometro bs01Hidrometro = coleta.getBs01HidrometroSet().stream()
                                    .filter(bs01HidrometroItem -> bs01HidrometroItem.getPonto().equals(ponto))
                                    .findFirst().orElse(null);


// Adiciona os dados principais da coleta com bordas
                            if (ponto.getNome().toUpperCase().contains("PB") && pb != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(pb.getPressao() != null ? pb.getPressao() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(pb.getPulsos() != null ? pb.getPulsos() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(pb.getNivel_oleo() != null ? pb.getNivel_oleo() : 0);
                                cell3.setCellStyle(borderStyle);

                                Cell cell4 = row.createCell(cellIndex++);
                                cell4.setCellValue(pb.getNivel_agua() != null ? pb.getNivel_agua() : 0);
                                cell4.setCellStyle(borderStyle);

                                Cell cell5 = row.createCell(cellIndex++);
                                cell5.setCellValue(pb.getVol_rem_oleo() != null ? pb.getVol_rem_oleo() : 0);
                                cell5.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("CD") && cd != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(cd.getPressao() != null ? cd.getPressao() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(cd.getHidrometro() != null ? cd.getHidrometro() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(cd.getTipo_rede() != null ? cd.getTipo_rede() : "");
                                cell3.setCellStyle(borderStyle);
                            } else if ((ponto.getNome().toUpperCase().contains("PM") || ponto.getNome().toUpperCase().contains("PT")) && pmpt != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(pmpt.getNivel_agua() != null ? pmpt.getNivel_agua() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(pmpt.getNivel_oleo() != null ? pmpt.getNivel_oleo() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(pmpt.getFl_remo_manual() != null ? pmpt.getFl_remo_manual() : 0);
                                cell3.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("SENSOR") && sensorPH != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(sensorPH.getPh() != null ? sensorPH.getPh() : 0);
                                cell1.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("FASE LIVRE") && faseLivre != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(faseLivre.getVolume() != null ? faseLivre.getVolume() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(!faseLivre.getHouve_troca() ? "NÃO" : "SIM");
                                cell2.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("TQ-01") && tq01 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(tq01.getNivel() != null ? tq01.getNivel() : 0);
                                cell1.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("BC-01") && bc01 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(bc01.getHorimetro() != null ? bc01.getHorimetro() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(bc01.getPressao() != null ? bc01.getPressao() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(bc01.getFrequencia() != null ? bc01.getFrequencia() : 0);
                                cell3.setCellStyle(borderStyle);

                                Cell cell4 = row.createCell(cellIndex++);
                                cell4.setCellValue(bc01.getVazao() != null ? bc01.getVazao() : 0);
                                cell4.setCellStyle(borderStyle);

                                Cell cell5 = row.createCell(cellIndex++);
                                cell5.setCellValue(bc01.getVolume() != null ? bc01.getVolume() : 0);
                                cell5.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("TQ-02") && tq02 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(tq02.getSensor_ph() != null ? tq02.getSensor_ph() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(tq02.getLt_02_1() != null ? tq02.getLt_02_1() : 0);
                                cell2.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("BH-02") && bh02 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(bh02.getHorimetro() != null ? bh02.getHorimetro() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(bh02.getPressao() != null ? bh02.getPressao() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(bh02.getFrequencia() != null ? bh02.getFrequencia() : 0);
                                cell3.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("FILTRO CARTUCHO") && filtroCartucho != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(filtroCartucho.getPressao_entrada() != null ? filtroCartucho.getPressao_entrada() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(filtroCartucho.getPressao_saida() != null ? filtroCartucho.getPressao_saida() : 0);
                                cell2.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("HORÍMETRO") && horimetro != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(horimetro.getHorimetro() != null ? horimetro.getHorimetro() : 0);
                                cell1.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("COLUNAS DE CARVÃO") && colunasCarvao != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(colunasCarvao.getPressao_c01() != null ? colunasCarvao.getPressao_c01() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(colunasCarvao.getPressao_c02() != null ? colunasCarvao.getPressao_c02() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(colunasCarvao.getPressao_c03() != null ? colunasCarvao.getPressao_c03() : 0);
                                cell3.setCellStyle(borderStyle);

                                Cell cell4 = row.createCell(cellIndex++);
                                cell4.setCellValue(colunasCarvao.getPressao_saida() != null ? colunasCarvao.getPressao_saida() : 0);
                                cell4.setCellStyle(borderStyle);

                                Cell cell5 = row.createCell(cellIndex++);
                                cell5.setCellValue(!colunasCarvao.getHouve_troca_carvao() ? "NÃO" : "SIM");
                                cell5.setCellStyle(borderStyle);

                                Cell cell6 = row.createCell(cellIndex++);
                                cell6.setCellValue(!colunasCarvao.getHouve_retrolavagem() ? "NÃO" : "SIM");
                                cell6.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("BC-03") && bombaBc03 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(bombaBc03.getPressao() != null ? bombaBc03.getPressao() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(bombaBc03.getHidrometro() != null ? bombaBc03.getHidrometro() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(bombaBc03.getHorimetro() != null ? bombaBc03.getHorimetro() : 0);
                                cell3.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("TQ-04") || ponto.getNome().toUpperCase().contains("TQ-05") && tq04Tq05 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(!tq04Tq05.getHouve_preparo_solucao() ? "NÃO" : "SIM");
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(tq04Tq05.getQtd_bombonas() != null ? tq04Tq05.getQtd_bombonas() : 0);
                                cell2.setCellStyle(borderStyle);

                                Cell cell3 = row.createCell(cellIndex++);
                                cell3.setCellValue(tq04Tq05.getKg_bombonas() != null ? tq04Tq05.getKg_bombonas() : 0);
                                cell3.setCellStyle(borderStyle);

                                Cell cell4 = row.createCell(cellIndex++);
                                cell4.setCellValue(tq04Tq05.getHorimetro() != null ? tq04Tq05.getHorimetro() : 0);
                                cell4.setCellStyle(borderStyle);

                                Cell cell5 = row.createCell(cellIndex++);
                                cell5.setCellValue(tq04Tq05.getHidrometro() != null ? tq04Tq05.getHidrometro() : 0);
                                cell5.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("BC-06") && bc06 != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(bc06.getPressao() != null ? bc06.getPressao() : 0);
                                cell1.setCellStyle(borderStyle);

                                Cell cell2 = row.createCell(cellIndex++);
                                cell2.setCellValue(bc06.getHorimetro() != null ? bc06.getHorimetro() : 0);
                                cell2.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("BS-01 PRESSÃO") && bs01Pressao != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(bs01Pressao.getPressao() != null ? bs01Pressao.getPressao() : 0);
                                cell1.setCellStyle(borderStyle);
                            } else if (ponto.getNome().toUpperCase().contains("BS-01 HIDRÔMETRO") && bs01Hidrometro != null) {
                                Cell cell1 = row.createCell(cellIndex++);
                                cell1.setCellValue(bs01Hidrometro.getVolume() != null ? bs01Hidrometro.getVolume() : 0);
                                cell1.setCellStyle(borderStyle);
                            }
                        }
                    }
                }
            }

            try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
                workbook.write(baos);

                File file = new File(exportPath + "/coletas.xlsx");
                try(FileOutputStream fos = new FileOutputStream(file)) {
                    fos.write(baos.toByteArray());
                }

                return new ByteArrayResource(baos.toByteArray());
            }

        }
    }
}