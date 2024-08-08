package com.wise.forms_coleta.dtos.colunas_carvao;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;

public record ColunasCarvaoPutDTO(@PositiveOrZero Double presssao_c01, @PositiveOrZero Double pressao_c02, @PositiveOrZero Double pressao_c03, @PositiveOrZero Double pressao_saida, Boolean houve_troca_carvao, Boolean houve_retrolavagem) {
}
