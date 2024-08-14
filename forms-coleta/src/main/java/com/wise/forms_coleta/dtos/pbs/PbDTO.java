package com.wise.forms_coleta.dtos.pbs;

import com.wise.forms_coleta.entities.PBs;

public record PbDTO(
        Long id,
        Double pressao,
        Double pulsos,
        Double nivel_oleo,
        Double nivel_agua,
        Double vol_rem_oleo
){
    public PbDTO(PBs pBs){
        this(pBs.getId(), pBs.getPressao(), pBs.getPulsos(), pBs.getNivel_oleo(), pBs.getNivel_agua(), pBs.getVol_rem_oleo());
    }
}
