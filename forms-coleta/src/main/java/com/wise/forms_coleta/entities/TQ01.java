package com.wise.forms_coleta.entities;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.tq01.TQ01CreateDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tq01")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TQ01 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nivel;

    // Relacionamentos

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "tq01Set", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public TQ01(TQ01CreateDTO data) {

        this.nivel = data.nivel();
    }
}
