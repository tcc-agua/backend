package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.services.exportar_excel.ExcelExportService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

public class ExcelExportHidrometroServiceImpl implements ExcelExportService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Autowired
    private ExcelRepository excelRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public ByteArrayResource exportToExcel(LocalDate startDate, LocalDate endDate) throws IOException {
        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            List<Hidrometro> hidrometros = hidrometroRepository.findAll();
            List<Excel> excels = excelRepository.findAll()
                    .stream()
                    .filter(excel -> Objects.equals(excel.getNome(), "CA")).toList();
            List<Coleta> coletasFiltradas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);

            for (Excel excel : excels) {
                if (!coletasFiltradas.isEmpty()) {
                    Sheet sheet = workbook.createSheet(excel.getNome());
                    int rowNum = 0;

                    // Criar cabeçalho
                    Row headerRow = sheet.createRow(rowNum++);
                    int cellIndex = 0;

                    // Cabeçalhos fixos: Item e Local
                    Cell itemHeaderCell = headerRow.createCell(cellIndex++, CellType.STRING);
                    itemHeaderCell.setCellValue("Item");

                    Cell localHeaderCell = headerRow.createCell(cellIndex++, CellType.STRING);
                    localHeaderCell.setCellValue("Local do Hidrometro");

                    // Gerar os cabeçalhos dos meses
                    LocalDate currentMonth = startDate.withDayOfMonth(1);
                    while (!currentMonth.isAfter(endDate)) {
                        Cell monthHeaderCell = headerRow.createCell(cellIndex++, CellType.STRING);
                        monthHeaderCell.setCellValue(currentMonth.getMonthValue() + "/" + currentMonth.getYear());
                        currentMonth = currentMonth.plusMonths(1);
                    }

                    // Preencher as linhas com os dados dos hidrômetros
                    for (Hidrometro hidrometro : hidrometros) {
                        Row dataRow = sheet.createRow(rowNum++);
                        int dataCellIndex = 0;

                        // Coluna 1: Item (id do hidrômetro)
                        Cell itemCell = dataRow.createCell(dataCellIndex++, CellType.NUMERIC);
                        itemCell.setCellValue(hidrometro.getId());

                        // Coluna 2: Local do ponto
                        Cell localCell = dataRow.createCell(dataCellIndex++, CellType.STRING);
                        localCell.setCellValue(hidrometro.getPonto().getLocalizacao());

                        // Colunas dos meses: somar volumes das coletas do hidrômetro em cada mês
                        LocalDate tempMonth = startDate.withDayOfMonth(1);  // Usar uma cópia para evitar erro
                        while (!tempMonth.isAfter(endDate)) {
                            final LocalDate current = tempMonth;  // A cópia final da variável para usar no lambda
                            double volumeTotal = coletasFiltradas.stream()
                                    .filter(coleta -> coleta.getHidrometroSet().contains(hidrometro)
                                            && coleta.getDataColeta().getMonthValue() == current.getMonthValue()
                                            && coleta.getDataColeta().getYear() == current.getYear())
                                    .mapToDouble(c -> hidrometro.getVolume())  // Pegar o volume do hidrômetro
                                    .sum();

                            Cell volumeCell = dataRow.createCell(dataCellIndex++, CellType.NUMERIC);
                            volumeCell.setCellValue(volumeTotal);

                            tempMonth = tempMonth.plusMonths(1);  // Incrementar o mês
                        }
                    }
                }
            }

            // Exportar o arquivo para ByteArrayResource
            ByteArrayResource resource;
            try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
                workbook.write(out);
                resource = new ByteArrayResource(out.toByteArray());
            }
            return resource;
        }
    }

}
