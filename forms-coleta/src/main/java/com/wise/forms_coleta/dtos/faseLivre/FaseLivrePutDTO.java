package com.wise.forms_coleta.dtos.faseLivre;

import jakarta.validation.constraints.PositiveOrZero;

public record FaseLivrePutDTO(@PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                              Double volume,

                              Boolean houve_troca) {
}
