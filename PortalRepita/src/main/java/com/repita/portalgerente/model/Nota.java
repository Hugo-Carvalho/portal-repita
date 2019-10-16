package com.repita.portalgerente.model;

import java.util.ArrayList;

public class Nota {

    private CadastroNota cadastroNota;

    private ArrayList<PDF> pdf;

    public CadastroNota getCadastroNota() {
        return cadastroNota;
    }

    public void setCadastroNota(CadastroNota cadastroNota) {
        this.cadastroNota = cadastroNota;
    }

    public ArrayList<PDF> getPdf() {
        return pdf;
    }

    public void setPdf(ArrayList<PDF> pdf) {
        this.pdf = pdf;
    }
}
