package com.wise.forms_coleta.dtos.ponto;

import com.wise.forms_coleta.entities.enums.StatusEnum;

public record PontoPutDTO(String nome, String localizacao, String excel, StatusEnum statusEnum) {
}
