package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.cd.CDCreateDTO;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="cd")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class CD {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String tipo_rede;
    private Double pressao;
    private Integer hidrometro;

    //Relacionamentos

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="ponto_id", referencedColumnName = "id")
    private Ponto ponto;

    @ManyToMany(mappedBy = "cdSet", fetch = FetchType.EAGER)
    private Set<Coleta> coletas = new HashSet<>();

    public CD(CDCreateDTO data) {
        this.tipo_rede = data.tipo_rede();
        this.pressao = data.pressao();
        this.hidrometro = data.hidrometro();
    }
}
