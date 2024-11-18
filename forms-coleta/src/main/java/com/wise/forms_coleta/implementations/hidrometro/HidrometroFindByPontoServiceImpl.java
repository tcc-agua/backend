package com.wise.forms_coleta.implementations.hidrometro;

import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.entities.Hidrometro;
import com.wise.forms_coleta.entities.Ponto;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.repositories.HidrometroRepository;
import com.wise.forms_coleta.repositories.PontoRepository;
import com.wise.forms_coleta.services.hidrometro.HidrometroFindByPontoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HidrometroFindByPontoServiceImpl implements HidrometroFindByPontoService {

    @Autowired
    private HidrometroRepository hidrometroRepository;

    @Autowired
    private PontoRepository pontoRepository;

    @Autowired
    private ColetaRepository coletaRepository;

    // Método para achar o hidrometro de acordo com o ponto
    @Override
    public List<Hidrometro> FindByPonto(String ponto, LocalDate startDate, LocalDate endDate) {
        // Pegando a instância de ponto associada à aquele nome
        Ponto ponto1 = pontoRepository.findByNome(ponto)
                .orElseThrow(() -> new GenericsNotFoundException("Ponto não encontrado!"));

        // Lista de coletas filtrando por data
        List<Coleta> coletas = coletaRepository.findAllByDataColetaBetween(startDate, endDate);

        // Iniciando uma lista de hidrometros
        List<Hidrometro> hidrometrosFiltrados = new ArrayList<>();

        // Para cada coleta dentro da lista de coletas pega os hidrometros associados aquela coleta e adiciona a lista de hidrometros
        for (Coleta coleta : coletas) {
            for (Hidrometro hidrometro : coleta.getHidrometroSet()) {
                if (hidrometro.getPonto().equals(ponto1)) {
                    hidrometrosFiltrados.add(hidrometro);
                }
            }
        }

        // Se a lista de hidrometros no final estiver vazia, um erro é retornado
        if (hidrometrosFiltrados.isEmpty()) {
            throw new GenericsNotFoundException("Nenhuma coleta de hidrômetro encontrada para esse ponto e intervalo de datas.");
        }

        // Retornando a lista
        return hidrometrosFiltrados;
    }

}