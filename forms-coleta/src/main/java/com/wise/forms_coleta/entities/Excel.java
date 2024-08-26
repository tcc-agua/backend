package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name="Excel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Excel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;

    private LocalDate data_coleta;

    @OneToMany(mappedBy = "excel", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Ponto ponto;

    public Excel(ExcelCreateDTO data){
        this.nome = data.nome();
    }
}
