package com.wise.forms_coleta.dtos.PmPt;

import com.wise.forms_coleta.entities.PmPt;

public record PmPtDTO(

        Long id,
        Double nivelAgua,
        Double nivelOleo,
        Double flRemoManual

){
    public PmPtDTO(PmPt pmPt){
        this(pmPt.getId(), pmPt.getNivel_agua(), pmPt.getNivel_oleo(), pmPt.getFl_remo_manual());
    }
}
