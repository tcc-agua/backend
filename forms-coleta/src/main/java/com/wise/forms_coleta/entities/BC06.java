package com.wise.forms_coleta.entities;

import com.wise.forms_coleta.dtos.BC06.BC06CreateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="BC-06")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BC06 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_ponto", referencedColumnName = "id")
    private Ponto fk_ponto;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="fk_coleta", referencedColumnName = "id")
    private Coleta fk_coleta;

    private String pressao;
    private String horimetro;

    public BC06(BC06CreateDTO data){
        this.pressao = data.pressao();
        this.horimetro = data.horimetro();
    }
}
