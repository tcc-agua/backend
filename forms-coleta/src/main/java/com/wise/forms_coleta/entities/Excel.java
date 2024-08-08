package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.excel.ExcelCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name="Excel")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Excel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="nome_excel")
    private String nomeExcel;
    private LocalDate data_coleta;

    @OneToOne(mappedBy = "fk_excel")
    private Ponto ponto;

    public Excel(ExcelCreateDTO data){
        this.nomeExcel = data.nome();
        this.data_coleta = data.data_coleta();
    }
}
