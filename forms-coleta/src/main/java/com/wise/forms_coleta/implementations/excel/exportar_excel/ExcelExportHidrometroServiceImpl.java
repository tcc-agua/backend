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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
                }
            }
        }
    }

}
