package com.wise.forms_coleta.implementations.coleta;

import com.wise.forms_coleta.dtos.coleta.ColetaDTO;
import com.wise.forms_coleta.entities.Coleta;
import com.wise.forms_coleta.exceptions.GenericsNotFoundException;
import com.wise.forms_coleta.repositories.ColetaRepository;
import com.wise.forms_coleta.services.coleta.ColetaGetUltimaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ColetaGetUltimaServiceImpl  implements ColetaGetUltimaService {

    @Autowired
    private ColetaRepository coletaRepository;

    // Método para pegar a última coleta
    @Override
    public ColetaDTO getLast() {
        // Retornando a lista de coletas existentes no banco
        List<Coleta> coletas = coletaRepository.findAll();
        // Pegando a última instância de coleta
        Coleta ultimaColeta = coletas.isEmpty() ? null : coletas.get(coletas.size() - 1);

        // Se a última instância recuperada não for nula, retorna um novo DTO
        if(ultimaColeta != null){
            return new ColetaDTO(ultimaColeta);
        }

        // Se for nula a exceção é lançada
        throw new GenericsNotFoundException("Coleta não encontrada");
    }
}
