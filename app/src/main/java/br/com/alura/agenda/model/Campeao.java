package br.com.alura.agenda.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Campeao implements Serializable {

    private int id = 0;
    private String nome;
    private String tipo;
    private String email;
    private String lane;
    private String dano;
    private String range;

    public Campeao() {

    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLane(String lane) {
        this.lane = lane;
    }

    public void setDano(String dano) {
        this.dano = dano;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public String getNome() {
        return nome;
    }

    public String getTipo() {
        return tipo;
    }

    public String getEmail() {
        return email;
    }

    public String getLane() {
        return lane;
    }

    public String getDano() {
        return dano;
    }

    public String getRange() {
        return range;
    }

    @NonNull
    @Override
    public String toString() {
        return nome + " - " + tipo + dano;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean temIdValido() {
        return id > 0;
    }


}
