package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.entities.*;
import com.wise.forms_coleta.repositories.*;
import com.wise.forms_coleta.services.coleta.ColetaGetAllPontosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ColetaGetAllPontosServiceImpl implements ColetaGetAllPontosService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public List<Map<String, Object>> getAllPontosByColeta(Long id) {
        List<Map<String, Object>> pontosColeta = new ArrayList<>();
        Optional<Coleta> coletaOptional = coletaRepository.findById(id);

        if (coletaOptional.isPresent()) {
            Coleta coleta = coletaOptional.get();

            // Adicionando BC01 e seu ponto associado
            for (BC01 bc01 : coleta.getBC01Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "BC01");
                pontoColeta.put("ponto", bc01.getPonto().getNome());
                pontoColeta.put("dados", bc01);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando BC06 e seu ponto associado
            for (BC06 bc06 : coleta.getBc06Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "BC06");
                pontoColeta.put("ponto", bc06.getPonto().getNome());
                pontoColeta.put("dados", bc06);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando BH02 e seu ponto associado
            for (BH02 bh02 : coleta.getBh02Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "BH02");
                pontoColeta.put("ponto", bh02.getPonto().getNome());
                pontoColeta.put("dados", bh02);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando BombaBc03 e seu ponto associado
            for (BombaBc03 bombaBc03 : coleta.getBombaBc03Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "BombaBc03");
                pontoColeta.put("ponto", bombaBc03.getPonto().getNome());
                pontoColeta.put("dados", bombaBc03);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando BS01Hidrometro e seu ponto associado
            for (BS01Hidrometro bs01Hidrometro : coleta.getBs01HidrometroSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "BS01Hidrometro");
                pontoColeta.put("ponto", bs01Hidrometro.getPonto().getNome());
                pontoColeta.put("dados", bs01Hidrometro);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando BS01Pressao e seu ponto associado
            for (BS01Pressao bs01Pressao : coleta.getBs01PressaoSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "BS01Pressao");
                pontoColeta.put("ponto", bs01Pressao.getPonto().getNome());
                pontoColeta.put("dados", bs01Pressao);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando CD e seu ponto associado
            for (CD cd : coleta.getCdSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "CD");
                pontoColeta.put("ponto", cd.getPonto().getNome());
                pontoColeta.put("dados", cd);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando ColunasCarvao e seu ponto associado
            for (ColunasCarvao colunasCarvao : coleta.getColunasCarvaoSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "ColunasCarvao");
                pontoColeta.put("ponto", colunasCarvao.getPonto().getNome());
                pontoColeta.put("dados", colunasCarvao);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando FaseLivre e seu ponto associado
            for (FaseLivre faseLivre : coleta.getFaseLivreSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "FaseLivre");
                pontoColeta.put("ponto", faseLivre.getPonto().getNome());
                pontoColeta.put("dados", faseLivre);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando FiltroCartucho e seu ponto associado
            for (FiltroCartucho filtroCartucho : coleta.getFiltroCartuchoSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "FiltroCartucho");
                pontoColeta.put("ponto", filtroCartucho.getPonto().getNome());
                pontoColeta.put("dados", filtroCartucho);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando Horimetro e seu ponto associado
            for (Horimetro horimetro : coleta.getHorimetroSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "Horimetro");
                pontoColeta.put("ponto", horimetro.getPonto().getNome());
                pontoColeta.put("dados", horimetro);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando PBs e seu ponto associado
            for (PBs pBs : coleta.getPbSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "PBs");
                pontoColeta.put("ponto", pBs.getPonto().getNome());
                pontoColeta.put("dados", pBs);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando PmPt e seu ponto associado
            for (PmPt pmPt : coleta.getPmPtSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "PmPt");
                pontoColeta.put("ponto", pmPt.getPonto().getNome());
                pontoColeta.put("dados", pmPt);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando SensorPH e seu ponto associado
            for (SensorPH sensorPH : coleta.getPhSet()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "SensorPH");
                pontoColeta.put("ponto", sensorPH.getPonto().getNome());
                pontoColeta.put("dados", sensorPH);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando TQ01 e seu ponto associado
            for (TQ01 tq01 : coleta.getTq01Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "TQ01");
                pontoColeta.put("ponto", tq01.getPonto().getNome());
                pontoColeta.put("dados", tq01);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando TQ02 e seu ponto associado
            for (TQ02 tq02 : coleta.getTq02Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "TQ02");
                pontoColeta.put("ponto", tq02.getPonto().getNome());
                pontoColeta.put("dados", tq02);
                pontosColeta.add(pontoColeta);
            }

            // Adicionando Tq04Tq05 e seu ponto associado
            for (Tq04Tq05 tq04Tq05 : coleta.getTq04Tq05Set()) {
                Map<String, Object> pontoColeta = new HashMap<>();
                pontoColeta.put("tipo", "Tq04Tq05");
                pontoColeta.put("ponto", tq04Tq05.getPonto().getNome());
                pontoColeta.put("dados", tq04Tq05);
                pontosColeta.add(pontoColeta);
            }
        }

        return pontosColeta;
    }

}
