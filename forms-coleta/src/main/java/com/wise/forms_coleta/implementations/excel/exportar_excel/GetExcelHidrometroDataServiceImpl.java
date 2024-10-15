package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Excel;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.services.exportar_excel.GetExcelHidrometroDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GetExcelHidrometroDataServiceImpl implements GetExcelHidrometroDataService {

    @Autowired
    private ExcelRepository excelRepository;
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public List<List<Object>> readExcelHidrometroFile(String sheetName, LocalDate startDate, LocalDate endDate, Boolean definicao) {
        List<Coleta> coletas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);
        Excel excel = excelRepository.findByNome(sheetName)
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        List<List<Object>> dadosComHeaders = new ArrayList<>();

        // Mapa para armazenar volumes por ponto
        Map<Long, Map<String, Double>> volumesPorHidrometro = new HashMap<>();
        Set<String> meses = new TreeSet<>(); // Para manter a ordem dos meses

        for (Coleta coleta : coletas) {
            String mesAno = coleta.getDataColeta().getMonthValue() + "/" + coleta.getDataColeta().getYear();
            meses.add(mesAno); // Adicionar o mês/ano para cabeçalho

            for (Hidrometro hidrometro : coleta.getHidrometroSet()) {
                if (hidrometro.getPonto().getExcel().equals(excel)) {
                    Long pontoId = hidrometro.getPonto().getId();

                    // Inicializar o mapa de volumes por ponto e mês, caso ainda não exista
                    volumesPorHidrometro.putIfAbsent(pontoId, new HashMap<>());
                    Map<String, Double> volumesMes = volumesPorHidrometro.get(pontoId);

                    // Adicionar o volume para o mês atual
                    volumesMes.put(mesAno, volumesMes.getOrDefault(mesAno, 0.0) + hidrometro.getVolume());
                }
            }
        }

        // Iterar sobre os pontos para compilar os dados
        for (Map.Entry<Long, Map<String, Double>> entry : volumesPorHidrometro.entrySet()) {
            Long pontoId = entry.getKey();
            Map<String, Double> volumesMes = entry.getValue();

            // Criar linha de dados para o ponto
            List<Object> dados = new ArrayList<>();
            // Adicionar nome do ponto
            dados.add(coletas.stream()
                    .flatMap(coleta -> coleta.getHidrometroSet().stream())
                    .filter(hidrometro -> hidrometro.getPonto().getId().equals(pontoId))
                    .findFirst()
                    .map(hidrometro -> hidrometro.getPonto().getNome())
                    .orElse("Nome não encontrado"));

            // Verificar se é a aba "CA" ou "Consumo das Áreas"
            if (definicao) {
                // Se for a aba CA, soma os volumes por mês
                Map<String, Object> dadosSemId = new HashMap<>();
                dadosSemId.put("volume", volumesMes); // Armazenar volumes por mês
                dados.add(dadosSemId);
            } else {
                // Se for a aba Consumo das Áreas, calcula a diferença
                Map<String, Object> dadosSemId = new HashMap<>();
                List<Double> diferencas = new ArrayList<>();

                String prevMes = null;
                for (String mes : meses) {
                    Double volumeAtual = volumesMes.getOrDefault(mes, 0.0);
                    Double diferenca = 0.0;

                    if (prevMes != null) {
                        // Calcular a diferença entre o mês atual e o anterior
                        Double volumeAnterior = volumesMes.getOrDefault(prevMes, 0.0);
                        diferenca = volumeAtual - volumeAnterior;
                    }
                    diferencas.add(diferenca);
                    prevMes = mes;
                }

                dadosSemId.put("diferenca", diferencas); // Armazenar as diferenças entre os meses
                dados.add(dadosSemId);
            }

            dadosComHeaders.add(dados); // Adicionar a linha de dados à lista de resultados
        }

        return dadosComHeaders;
    }


}
