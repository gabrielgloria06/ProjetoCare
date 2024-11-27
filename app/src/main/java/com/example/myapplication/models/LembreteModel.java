package com.example.myapplication.models;

public class LembreteModel {

     String nome, dia, hora, local;
     boolean feito ;


    public LembreteModel(String nome, String dia, String hora, String local, boolean feito) {
        this.nome = nome;
        this.dia = dia;
        this.hora = hora;
        this.local = local;
        this.feito = feito;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public boolean isFeito() {
        return feito;
    }

    public void setFeito(boolean feito) {
        this.feito = feito;
    }
}
