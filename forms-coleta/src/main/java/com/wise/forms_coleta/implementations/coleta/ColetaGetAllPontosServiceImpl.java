package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.entities.*;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaGetAllPontosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ColetaGetAllPontosServiceImpl implements ColetaGetAllPontosService {

    @Autowired
    private ColetaRepository coletaRepository;

    @Override
    public Page<Map<String, Object>> getAllPontosByDate(LocalDate startDate, LocalDate endDate, Pageable pageable) {
        // Filtra as coletas por data com paginação
        Page<Coleta> coletas = coletaRepository.findAllByDataColetaBetween(startDate, endDate, pageable);

        // Converte a lista de Coleta para Map<String, Object>
        List<Map<String, Object>> coletasComPontos = coletas.stream().map(coleta -> {
            Map<String, Object> coletaData = new LinkedHashMap<>(); // Usa LinkedHashMap para manter a ordem dos campos
            coletaData.put("id", coleta.getId()); // Adiciona o id da coleta
            coletaData.put("date", coleta.getDataColeta().toString()); // Ajuste o formato da data conforme necessário

            // Formatações de data e hora
            DateTimeFormatter dateFormatterForDateField = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String formattedDateForDateField = coleta.getDataColeta().format(dateFormatterForDateField);
            coletaData.put("date", formattedDateForDateField);

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd 'de' MMMM 'de' yyyy", new Locale("pt", "BR"));
            String formattedDate = coleta.getDataColeta().format(dateFormatter);
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String formattedTime = coleta.getHora_inicio().format(timeFormatter);

            // Adiciona manualmente o fuso horário "BRT" à string formatada
            String description = formattedDate + ", " + formattedTime + " BRT";

            coletaData.put("description", description); // Usa a data formatada na descrição

            // Adiciona os pontos da coleta
            List<Map<String, Object>> pontosColeta = new ArrayList<>();

            // Exemplo de como adicionar pontos
            for (BC01 bc01 : coleta.getBC01Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", bc01.getId());
                pontoColeta.put("tipo", "BC01");
                pontoColeta.put("ponto", bc01.getPonto().getNome());
                pontoColeta.put("dados", bc01);
                pontosColeta.add(pontoColeta);
            }

            for (BC06 bc06 : coleta.getBc06Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", bc06.getId());
                pontoColeta.put("tipo", "BC06");
                pontoColeta.put("ponto", bc06.getPonto().getNome());
                pontoColeta.put("dados", bc06);
                pontosColeta.add(pontoColeta);
            }

            for (BH02 bh02 : coleta.getBh02Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", bh02.getId());
                pontoColeta.put("tipo", "BH02");
                pontoColeta.put("ponto", bh02.getPonto().getNome());
                pontoColeta.put("dados", bh02);
                pontosColeta.add(pontoColeta);
            }

            for (BombaBc03 bombaBc03 : coleta.getBombaBc03Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", bombaBc03.getId());
                pontoColeta.put("tipo", "BombaBc03");
                pontoColeta.put("ponto", bombaBc03.getPonto().getNome());
                pontoColeta.put("dados", bombaBc03);
                pontosColeta.add(pontoColeta);
            }

            for (BS01Hidrometro bs01Hidrometro : coleta.getBs01HidrometroSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", bs01Hidrometro.getId());
                pontoColeta.put("tipo", "BS01Hidrometro");
                pontoColeta.put("ponto", bs01Hidrometro.getPonto().getNome());
                pontoColeta.put("dados", bs01Hidrometro);
                pontosColeta.add(pontoColeta);
            }

            for (BS01Pressao bs01Pressao : coleta.getBs01PressaoSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", bs01Pressao.getId());
                pontoColeta.put("tipo", "BS01Pressao");
                pontoColeta.put("ponto", bs01Pressao.getPonto().getNome());
                pontoColeta.put("dados", bs01Pressao);
                pontosColeta.add(pontoColeta);
            }

            for (CD cd : coleta.getCdSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", cd.getId());
                pontoColeta.put("tipo", "CD");
                pontoColeta.put("ponto", cd.getPonto().getNome());
                pontoColeta.put("dados", cd);
                pontosColeta.add(pontoColeta);
            }

            for (ColunasCarvao colunasCarvao : coleta.getColunasCarvaoSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", colunasCarvao.getId());
                pontoColeta.put("tipo", "ColunasCarvao");
                pontoColeta.put("ponto", colunasCarvao.getPonto().getNome());
                pontoColeta.put("dados", colunasCarvao);
                pontosColeta.add(pontoColeta);
            }

            for (FaseLivre faseLivre : coleta.getFaseLivreSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", faseLivre.getId());
                pontoColeta.put("tipo", "FaseLivre");
                pontoColeta.put("ponto", faseLivre.getPonto().getNome());
                pontoColeta.put("dados", faseLivre);
                pontosColeta.add(pontoColeta);
            }

            for (FiltroCartucho filtroCartucho : coleta.getFiltroCartuchoSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", filtroCartucho.getId());
                pontoColeta.put("tipo", "FiltroCartucho");
                pontoColeta.put("ponto", filtroCartucho.getPonto().getNome());
                pontoColeta.put("dados", filtroCartucho);
                pontosColeta.add(pontoColeta);
            }

            for (Horimetro horimetro : coleta.getHorimetroSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", horimetro.getId());
                pontoColeta.put("tipo", "Horimetro");
                pontoColeta.put("ponto", horimetro.getPonto().getNome());
                pontoColeta.put("dados", horimetro);
                pontosColeta.add(pontoColeta);
            }

            for (PBs pBs : coleta.getPbSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", pBs.getId());
                pontoColeta.put("tipo", "PBs");
                pontoColeta.put("ponto", pBs.getPonto().getNome());
                pontoColeta.put("dados", pBs);
                pontosColeta.add(pontoColeta);
            }

            for (PmPt pmPt : coleta.getPmPtSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", pmPt.getId());
                pontoColeta.put("tipo", "PmPt");
                pontoColeta.put("ponto", pmPt.getPonto().getNome());
                pontoColeta.put("dados", pmPt);
                pontosColeta.add(pontoColeta);
            }

            for (SensorPH sensorPH : coleta.getPhSet()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", sensorPH.getId());
                pontoColeta.put("tipo", "SensorPH");
                pontoColeta.put("ponto", sensorPH.getPonto().getNome());
                pontoColeta.put("dados", sensorPH);
                pontosColeta.add(pontoColeta);
            }

            for (TQ01 tq01 : coleta.getTq01Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", tq01.getId());
                pontoColeta.put("tipo", "TQ01");
                pontoColeta.put("ponto", tq01.getPonto().getNome());
                pontoColeta.put("dados", tq01);
                pontosColeta.add(pontoColeta);
            }

            for (TQ02 tq02 : coleta.getTq02Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", tq02.getId());
                pontoColeta.put("tipo", "TQ02");
                pontoColeta.put("ponto", tq02.getPonto().getNome());
                pontoColeta.put("dados", tq02);
                pontosColeta.add(pontoColeta);
            }

            for (Tq04Tq05 tq04Tq05 : coleta.getTq04Tq05Set()) {
                Map<String, Object> pontoColeta = new LinkedHashMap<>();
                pontoColeta.put("id", tq04Tq05.getId());
                pontoColeta.put("tipo", "TQ04TQ05");
                pontoColeta.put("ponto", tq04Tq05.getPonto().getNome());
                pontoColeta.put("dados", tq04Tq05);
                pontosColeta.add(pontoColeta);
            }

            coletaData.put("details", pontosColeta);
            return coletaData;
        }).collect(Collectors.toList());

        // Retorna a lista convertida como um Page utilizando o PageImpl
        return new PageImpl<>(coletasComPontos, pageable, coletas.getTotalElements());
    }
}
