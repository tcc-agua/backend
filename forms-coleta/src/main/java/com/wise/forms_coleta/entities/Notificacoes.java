package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.notificacao.NotifCreateDTO;
import com.wise.forms_coleta.entities.enums.NotifEnum;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "Notificacoes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Notificacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tabela;

    @Enumerated(EnumType.STRING)
    private NotifEnum tipo;

    private LocalDate data;

    public Notificacoes(NotifCreateDTO data){
        this.tabela = data.tabela();
        this.tipo = data.tipo();
        this.data = LocalDate.now();
    }

}
