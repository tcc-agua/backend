package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.coleta.ColetaCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(name="Coleta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coleta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tecnico;

    @Column(name="data_coleta")
    private LocalDate dataColeta;

    @Column(name="hora_inicio")
    private LocalTime horaInicio;

    @Column(name="hora_fim")
    private LocalTime horaFim;

    @OneToOne(mappedBy = "fk_coleta")
    private ColunasCarvao colunasCarvao;

    public Coleta(ColetaCreateDTO data){
        this.tecnico = data.tecnico();
        this.dataColeta = data.dataColeta();
        this.horaInicio = data.horaInicio();
        this.horaFim = data.horaFim();
    }
}
