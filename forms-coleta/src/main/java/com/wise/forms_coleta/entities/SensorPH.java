package com.wise.forms_coleta.entities;


import com.wise.forms_coleta.dtos.sensor_ph.SensorPHCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="sensor_ph")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class SensorPH {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double ph;

    // Relacionamentos

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    private Ponto ponto;

    @ManyToMany(mappedBy = "sensorPHSet", fetch = FetchType.EAGER)
    private Set<Coleta> coletas = new HashSet<>();

    public SensorPH(SensorPHCreateDTO data) {
        this.ph = data.ph();
    }
}
