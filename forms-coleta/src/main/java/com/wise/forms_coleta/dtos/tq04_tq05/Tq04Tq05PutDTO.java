package com.wise.forms_coleta.dtos.tq04_tq05;

import jakarta.validation.constraints.PositiveOrZero;

public record Tq04Tq05PutDTO(        Boolean houve_preparo_solucao,

                                     @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                                     Integer qtd_bombonas,

                                     @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                                     Double kg_bombonas,

                                     @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                                     Double horimetro,

                                     @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                                     Double hidrometro) {
}
