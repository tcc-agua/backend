package com.wise.forms_coleta.implementations.excel.exportar_excel;

import com.wise.forms_coleta.entities.*;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.ExcelRepository;
import com.wise.forms_coleta.services.exportar_excel.GetExcelDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GetExcelDataServiceImpl implements GetExcelDataService {

    @Autowired
    private ExcelRepository excelRepository;
    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public Map<String, List<Object>> readExcelFile(String sheetName) {
        // Filtra as coletas por data
        List<Coleta> coletas = coletaRepository.findAll();
        Excel excel = excelRepository.findByNome(sheetName)
                .orElseThrow(() -> new GenericsNotFoundException("Excel não encontrado!"));

        List<Object> headers = new ArrayList<>();
        List<Object> dados = new ArrayList<>();
        Set<Object> headerSet = new HashSet<>();  // Para evitar duplicados nos headers

        for (Coleta coleta : coletas) {
            for (BC01 bc01 : coleta.getBC01Set()) {
                if (bc01.getPonto().getExcel() == excel) {
                    if (headerSet.add(bc01.getPonto().getNome())) {
                        headers.add(bc01.getPonto().getNome());
                    }
                    dados.add(bc01);
                }
            }

            for (BC06 bc06 : coleta.getBc06Set()) {
                if (bc06.getPonto().getExcel() == excel) {
                    if (headerSet.add(bc06.getPonto().getNome())) {
                        headers.add(bc06.getPonto().getNome());
                    }
                    dados.add(bc06);
                }
            }

            for (BH02 bh02 : coleta.getBh02Set()) {
                if (bh02.getPonto().getExcel() == excel) {
                    if (headerSet.add(bh02.getPonto().getNome())) {
                        headers.add(bh02.getPonto().getNome());
                    }
                    dados.add(bh02);
                }
            }

            for (BombaBc03 bombaBc03 : coleta.getBombaBc03Set()) {
                if (bombaBc03.getPonto().getExcel() == excel) {
                    if (headerSet.add(bombaBc03.getPonto().getNome())) {
                        headers.add(bombaBc03.getPonto().getNome());
                    }
                    dados.add(bombaBc03);
                }
            }

            for (BS01Hidrometro bs01Hidrometro : coleta.getBs01HidrometroSet()) {
                if (bs01Hidrometro.getPonto().getExcel() == excel) {
                    if (headerSet.add(bs01Hidrometro.getPonto().getNome())) {
                        headers.add(bs01Hidrometro.getPonto().getNome());
                    }
                    dados.add(bs01Hidrometro);
                }
            }

            for (BS01Pressao bs01Pressao : coleta.getBs01PressaoSet()) {
                if (bs01Pressao.getPonto().getExcel() == excel) {
                    if (headerSet.add(bs01Pressao.getPonto().getNome())) {
                        headers.add(bs01Pressao.getPonto().getNome());
                    }
                    dados.add(bs01Pressao);
                }
            }

            for (CD cd : coleta.getCdSet()) {
                if (cd.getPonto().getExcel() == excel) {
                    if (headerSet.add(cd.getPonto().getNome())) {
                        headers.add(cd.getPonto().getNome());
                    }
                    dados.add(cd);
                }
            }

            for (ColunasCarvao colunasCarvao : coleta.getColunasCarvaoSet()) {
                if (colunasCarvao.getPonto().getExcel() == excel) {
                    if (headerSet.add(colunasCarvao.getPonto().getNome())) {
                        headers.add(colunasCarvao.getPonto().getNome());
                    }
                    dados.add(colunasCarvao);
                }
            }

            for (FaseLivre faseLivre : coleta.getFaseLivreSet()) {
                if (faseLivre.getPonto().getExcel() == excel) {
                    if (headerSet.add(faseLivre.getPonto().getNome())) {
                        headers.add(faseLivre.getPonto().getNome());
                    }
                    dados.add(faseLivre);
                }
            }

            for (FiltroCartucho filtroCartucho : coleta.getFiltroCartuchoSet()) {
                if (filtroCartucho.getPonto().getExcel() == excel) {
                    if (headerSet.add(filtroCartucho.getPonto().getNome())) {
                        headers.add(filtroCartucho.getPonto().getNome());
                    }
                    dados.add(filtroCartucho);
                }
            }

            for (Horimetro horimetro : coleta.getHorimetroSet()) {
                if (horimetro.getPonto().getExcel() == excel) {
                    if (headerSet.add(horimetro.getPonto().getNome())) {
                        headers.add(horimetro.getPonto().getNome());
                    }
                    dados.add(horimetro);
                }
            }

            for (PBs pBs : coleta.getPbSet()) {
                if (pBs.getPonto().getExcel() == excel) {
                    if (headerSet.add(pBs.getPonto().getNome())) {
                        headers.add(pBs.getPonto().getNome());
                    }
                    dados.add(pBs);
                }
            }

            for (PmPt pmPt : coleta.getPmPtSet()) {
                if (pmPt.getPonto().getExcel() == excel) {
                    if (headerSet.add(pmPt.getPonto().getNome())) {
                        headers.add(pmPt.getPonto().getNome());
                    }
                    dados.add(pmPt);
                }
            }

            for (SensorPH sensorPH : coleta.getPhSet()) {
                if (sensorPH.getPonto().getExcel() == excel) {
                    if (headerSet.add(sensorPH.getPonto().getNome())) {
                        headers.add(sensorPH.getPonto().getNome());
                    }
                    dados.add(sensorPH);
                }
            }

            for (TQ01 tq01 : coleta.getTq01Set()) {
                if (tq01.getPonto().getExcel() == excel) {
                    if (headerSet.add(tq01.getPonto().getNome())) {
                        headers.add(tq01.getPonto().getNome());
                    }
                    dados.add(tq01);
                }
            }

            for (TQ02 tq02 : coleta.getTq02Set()) {
                if (tq02.getPonto().getExcel() == excel) {
                    if (headerSet.add(tq02.getPonto().getNome())) {
                        headers.add(tq02.getPonto().getNome());
                    }
                    dados.add(tq02);
                }
            }

            for (Tq04Tq05 tq04Tq05 : coleta.getTq04Tq05Set()) {
                if (tq04Tq05.getPonto().getExcel() == excel) {
                    if (headerSet.add(tq04Tq05.getPonto().getNome())) {
                        headers.add(tq04Tq05.getPonto().getNome());
                    }
                    dados.add(tq04Tq05);
                }
            }
        }

        // Monta o mapa com headers e dados
        Map<String, List<Object>> result = new LinkedHashMap<>();
        result.put("headers", headers);
        result.put("dados", dados);

        return result;
    }
}
