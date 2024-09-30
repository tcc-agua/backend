package com.wise.forms_coleta.dtos.bomba_bc03;

import jakarta.validation.constraints.PositiveOrZero;

public record BombaBc03PutDTO(@PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                              Double pressao,

                              @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                              Double hidrometro,

                              @PositiveOrZero(message = "O número valor precisa ser maior ou igual a 0!")
                              Double horimetro) {
}
