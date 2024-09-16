package com.wise.forms_coleta.implementations.excel.exportar_excel;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class GetExcelDataServiceImpl implements GetExcelDataService {

    private static final String FILE_PATH = "C:/Users/ct67ca/Desktop/backend/forms-coleta/src/main/java/com/wise/forms_coleta/implementations/excel/excel_file/coletas.xlsx";

    @Override
    public List<List<String>> readExcelFile(String sheetName) throws IOException {
        List<List<String>> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet with name '" + sheetName + "' does not exist.");
            }

            for (Row row : sheet) {
                List<String> rowData = new ArrayList<>();
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            rowData.add(cell.getStringCellValue());
                            break;
                        case NUMERIC:
                            rowData.add(String.valueOf(cell.getNumericCellValue()));
                            break;
                        case BOOLEAN:
                            rowData.add(String.valueOf(cell.getBooleanCellValue()));
                            break;
                        default:
                            rowData.add("");
                    }
                }
                data.add(rowData);
            }
        }

        return data;
    }
}
