package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.TQ02.TQ02CreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tq02")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class TQ02 {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double sensor_ph;
    private Double lt_02_1;

    @ManyToOne
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "tq02Set", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public TQ02(TQ02CreateDTO data) {
        this.sensor_ph = data.sensor_ph();
        this.lt_02_1 = data.Lt_02_1();
    }
}
