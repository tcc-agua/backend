package com.wise.forms_coleta.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroCreateDTO;
import com.wise.forms_coleta.dtos.hidrometro.HidrometroDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="hidrometro")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Hidrometro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double volume;

    // Relacionamentos

    @ManyToOne
    @JoinColumn(name = "ponto_id", referencedColumnName = "id")
    @JsonBackReference
    private Ponto ponto;

    @ManyToMany(mappedBy = "hidrometroSet", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Coleta> coletas = new HashSet<>();

    public Hidrometro(HidrometroDTO data) { this.volume = data.volume();}

    public Hidrometro(HidrometroCreateDTO data) {
        this.volume = data.volume();

    }
}
