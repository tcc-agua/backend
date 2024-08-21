package com.wise.forms_coleta.implementations.exportar_excel;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.PBs;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.AbstractMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ByteArrayResource exportToExcel() throws IOException {
        try(XSSFWorkbook workbook = new XSSFWorkbook()) {
            List<Coleta> coletas = coletaRepository.findAll();

            // Agrupando os dados por ponto
            Map<String, List<Coleta>> dadosPorPonto = coletas.stream()
                    .flatMap(coleta -> coleta.getPbSet().stream()
                            .map(pb -> new AbstractMap.SimpleEntry<>(pb.getPonto().getNome(), coleta)))
                    .collect(Collectors.groupingBy(Map.Entry::getKey,
                            Collectors.mapping(Map.Entry::getValue, Collectors.toList())));

            // Criando uma aba pra cada ponto
            for (Map.Entry<String, List<Coleta>> entry : dadosPorPonto.entrySet()){
                String pontoNome = entry.getKey();
                List<Coleta> coletasDoPonto = entry.getValue();

                // Criando uma nova aba no excel com o nome do ponto
                Sheet sheet = workbook.createSheet(pontoNome);
                writeDataToSheet(sheet, coletasDoPonto);
            }

            // Escrever os dados do workbook no output stream e retornar como recurso
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()){
                workbook.write(outputStream);
                return new ByteArrayResource(outputStream.toByteArray());
            }
        }
    }

    private void writeDataToSheet(Sheet sheet, List<Coleta> coletas) {
        // Adiciona o cabeçalho
        Row headerRow = sheet.createRow(0);
        String[] header = {
                "Técnico", "Data Coleta", "Hora Início", "Hora Fim", "Pressão",
                "Pulsos", "Nível de Óleo", "Nível d'água", "Volume Manual Removido de Óleo"
        };
        for (int i = 0; i < header.length; i++) {
            headerRow.createCell(i).setCellValue(header[i]);
        }

        // Adiciona os dados das coletas
        int rowNum = 1;
        for (Coleta coleta : coletas) {
            for (PBs pb : coleta.getPbSet()) {
                Row row = sheet.createRow(rowNum++);

                String tecnico = coleta.getTecnico();
                String dataColeta = coleta.getDataColeta().toString();
                String horaInicio = coleta.getHora_inicio().toString();
                String horaFim = coleta.getHora_fim().toString();
                double pressao = pb.getPressao();
                Double pulsos = pb.getPulsos();
                double nivelOleo = pb.getNivel_oleo();
                double nivelAgua = pb.getNivel_agua();
                double volRemOleo = pb.getVol_rem_oleo();

                // Printando os dados no console
                System.out.println("Tecnico: " + tecnico);
                System.out.println("Data Coleta: " + dataColeta);
                System.out.println("Hora Início: " + horaInicio);
                System.out.println("Hora Fim: " + horaFim);
                System.out.println("Pressão: " + pressao);
                System.out.println("Pulsos: " + pulsos);
                System.out.println("Nível de Óleo: " + nivelOleo);
                System.out.println("Nível d'água: " + nivelAgua);
                System.out.println("Volume Manual Removido de Óleo: " + volRemOleo);
                System.out.println("-------------------------------");

                // Escrevendo os dados no Excel
                row.createCell(0).setCellValue(tecnico);
                row.createCell(1).setCellValue(dataColeta);
                row.createCell(2).setCellValue(horaInicio);
                row.createCell(3).setCellValue(horaFim);
                row.createCell(4).setCellValue(pressao);
                row.createCell(5).setCellValue(pulsos);
                row.createCell(6).setCellValue(nivelOleo);
                row.createCell(7).setCellValue(nivelAgua);
                row.createCell(8).setCellValue(volRemOleo);
            }
        }
    }
}
