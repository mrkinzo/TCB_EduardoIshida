package br.edu.ifpr.model;

import java.time.LocalDate;
import java.util.List;

public class Emprestimo {
    private int idemprestimo;
    private String dataEmp;
    private String dataDev;
    private int usuarioId;
    private List<Mineral> minerais;
    private List<Rocha> rochas;

    // Construtores
    public Emprestimo() {}

    public Emprestimo(int idemprestimo, String dataEmp, String dataDev, int usuarioId) {
        this.idemprestimo = idemprestimo;
        this.dataEmp = dataEmp;
        this.dataDev = dataDev;
        this.usuarioId = usuarioId;
    }

    // Getters e Setters
    public int getIdemprestimo() {
        return idemprestimo;
    }

    public void setIdemprestimo(int idemprestimo) {
        this.idemprestimo = idemprestimo;
    }

    public String getDataEmp() {
        return dataEmp;
    }

    public void setDataEmp(String dataEmp) {
        this.dataEmp = dataEmp;
    }

    public String getDataDev() {
        return dataDev;
    }

    public void setDataDev(String dataDev) {
        this.dataDev = dataDev;
    }

    public int getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(int usuarioId) {
        this.usuarioId = usuarioId;
    }

    public List<Mineral> getMinerais() {
        return minerais;
    }

    public void setMinerais(List<Mineral> minerais) {
        this.minerais = minerais;
    }

    public List<Rocha> getRochas() {
        return rochas;
    }

    public void setRochas(List<Rocha> rochas) {
        this.rochas = rochas;
    }

    @Override
    public String toString() {
        return "Emprestimo #" + idemprestimo + " - Data: " + dataEmp + " a " + dataDev;
    }
}