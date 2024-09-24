package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.*;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class GetExcelDataServiceImpl implements GetExcelDataService {

    @Autowired
    private ExcelRepository excelRepository;
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public List<List<Object>> readExcelFile(String sheetName, LocalDate startDate, LocalDate endDate) {
        // Filtra as coletas por data
        List<Coleta> coletas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);
        Excel excel = excelRepository.findByNome(sheetName)
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        List<List<Object>> dadosComHeaders = new ArrayList<>(); // Lista final com os headers e dados

        for (Coleta coleta : coletas) {
            for (BC01 bc01 : coleta.getBC01Set()) {
                if (bc01.getPonto().getExcel() == excel) {
                    // Adiciona o header como primeiro elemento
                    List<Object> dados = new ArrayList<>();
                    dados.add(bc01.getPonto().getNome()); // Primeiro elemento é o header
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dadosSemId.put("horimetro", bc01.getHorimetro());
                    dadosSemId.put("pressao", bc01.getPressao());
                    dadosSemId.put("frequencia", bc01.getFrequencia());
                    dadosSemId.put("vazao", bc01.getVazao());
                    dadosSemId.put("volume", bc01.getVolume());
                    dados.add(dadosSemId); // Adiciona os dados sem id
                    dadosComHeaders.add(dados);
                }
            }

            for (BC06 bc06 : coleta.getBc06Set()) {
                if (bc06.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(bc06.getPonto().getNome());
                    dadosSemId.put("pressao", bc06.getPressao());
                    dadosSemId.put("horimetro", bc06.getHorimetro());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (BH02 bh02 : coleta.getBh02Set()) {
                if (bh02.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(bh02.getPonto().getNome());
                    dadosSemId.put("horimetro", bh02.getHorimetro());
                    dadosSemId.put("pressao", bh02.getPressao());
                    dadosSemId.put("frequencia", bh02.getFrequencia());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (BombaBc03 bombaBc03 : coleta.getBombaBc03Set()) {
                if (bombaBc03.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(bombaBc03.getPonto().getNome());
                    dadosSemId.put("pressao", bombaBc03.getHorimetro());
                    dadosSemId.put("hidrometro", bombaBc03.getHidrometro());
                    dadosSemId.put("horimetro", bombaBc03.getHorimetro());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (BS01Hidrometro bs01Hidrometro : coleta.getBs01HidrometroSet()) {
                if (bs01Hidrometro.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(bs01Hidrometro.getPonto().getNome());
                    dadosSemId.put("volume", bs01Hidrometro.getVolume());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (BS01Pressao bs01Pressao : coleta.getBs01PressaoSet()) {
                if (bs01Pressao.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(bs01Pressao.getPonto().getNome());
                    dadosSemId.put("pressao", bs01Pressao.getPressao());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (CD cd : coleta.getCdSet()) {
                if (cd.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(cd.getPonto().getNome());
                    dadosSemId.put("tipo_rede", cd.getTipo_rede());
                    dadosSemId.put("pressao", cd.getPressao());
                    dadosSemId.put("hidrometro", cd.getHidrometro());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (ColunasCarvao colunasCarvao : coleta.getColunasCarvaoSet()) {
                if (colunasCarvao.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(colunasCarvao.getPonto().getNome());
                    dadosSemId.put("pressao_c01", colunasCarvao.getPressao_c01());
                    dadosSemId.put("pressao_c02", colunasCarvao.getPressao_c02());
                    dadosSemId.put("pressao_c03", colunasCarvao.getPressao_c03());
                    dadosSemId.put("pressao_saida", colunasCarvao.getPressao_saida());
                    dadosSemId.put("houve_troca_carvao", colunasCarvao.getHouve_troca_carvao());
                    dadosSemId.put("houve_retrolavagem", colunasCarvao.getHouve_retrolavagem());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (FaseLivre faseLivre : coleta.getFaseLivreSet()) {
                if (faseLivre.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(faseLivre.getPonto().getNome());
                    dadosSemId.put("volume", faseLivre.getVolume());
                    dadosSemId.put("houve_troca", faseLivre.getHouve_troca());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (FiltroCartucho filtroCartucho : coleta.getFiltroCartuchoSet()) {
                if (filtroCartucho.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(filtroCartucho.getPonto().getNome());
                    dadosSemId.put("pressao_entrada", filtroCartucho.getPressao_entrada());
                    dadosSemId.put("pressao_saida", filtroCartucho.getPressao_saida());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (Horimetro horimetro : coleta.getHorimetroSet()) {
                if (horimetro.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(horimetro.getPonto().getNome());
                    dadosSemId.put("horimetro", horimetro.getHorimetro());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (PBs pBs : coleta.getPbSet()) {
                if (pBs.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(pBs.getPonto().getNome());
                    dadosSemId.put("pressao", pBs.getPressao());
                    dadosSemId.put("pulsos", pBs.getPulsos());
                    dadosSemId.put("nivel_oleo", pBs.getNivel_oleo());
                    dadosSemId.put("nivel_agua", pBs.getNivel_agua());
                    dadosSemId.put("vol_rem_oleo", pBs.getVol_rem_oleo());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (PmPt pmPt : coleta.getPmPtSet()) {
                if (pmPt.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(pmPt.getPonto().getNome());
                    dadosSemId.put("nivel_agua", pmPt.getNivel_agua());
                    dadosSemId.put("nivel_oleo", pmPt.getNivel_oleo());
                    dadosSemId.put("fl_remo_manual", pmPt.getFl_remo_manual());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (SensorPH sensorPH : coleta.getPhSet()) {
                if (sensorPH.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(sensorPH.getPonto().getNome());
                    dadosSemId.put("ph", sensorPH.getPh());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (TQ01 tq01 : coleta.getTq01Set()) {
                if (tq01.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(tq01.getPonto().getNome());
                    dadosSemId.put("nivel", tq01.getNivel());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (TQ02 tq02 : coleta.getTq02Set()) {
                if (tq02.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(tq02.getPonto().getNome());
                    dadosSemId.put("sensor_ph", tq02.getSensor_ph());
                    dadosSemId.put("lt_02_1", tq02.getLt_02_1());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }

            for (Tq04Tq05 tq04Tq05 : coleta.getTq04Tq05Set()) {
                if (tq04Tq05.getPonto().getExcel() == excel) {
                    List<Object> dados = new ArrayList<>();
                    Map<String, Object> dadosSemId = new HashMap<>();
                    dados.add(tq04Tq05.getPonto().getNome());
                    dadosSemId.put("houve_preparo_solucao", tq04Tq05.getHouve_preparo_solucao());
                    dadosSemId.put("qtd_bombonas", tq04Tq05.getQtd_bombonas());
                    dadosSemId.put("kg_bombonas", tq04Tq05.getKg_bombonas());
                    dadosSemId.put("horimetro", tq04Tq05.getHorimetro());
                    dadosSemId.put("hidrometro", tq04Tq05.getHidrometro());
                    dados.add(dadosSemId);
                    dadosComHeaders.add(dados);
                }
            }
        }

        return dadosComHeaders; // Retorna a lista com os headers e objetos
    }
}