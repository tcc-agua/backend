package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.wise.forms_coleta.dtos.horimetro.HorimetroCreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="horimetro")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Horimetro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String horimetro;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonIgnore
    private Ponto ponto;

    @ManyToMany(mappedBy = "horimetroSet", fetch = FetchType.EAGER)
    private Set<Coleta> coletas = new HashSet<>();

    public Horimetro(HorimetroCreateDTO data){
        this.horimetro = data.horimetro();
    }
}
