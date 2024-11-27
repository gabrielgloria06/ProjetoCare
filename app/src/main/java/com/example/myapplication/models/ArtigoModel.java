package com.example.myapplication.models;

public class ArtigoModel {
    private int id;
    private String titulo;
    private int imagem; // Use um tipo apropriado para a imagem, se necessário
    private String conteudo;
    private String autor;
    private String dataPublicacao;

    // Construtor
    public ArtigoModel(int id, String titulo, int imagem, String conteudo, String autor, String dataPublicacao) {
        this.id = id;
        this.titulo = titulo;
        this.imagem = imagem;
        this.conteudo = conteudo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(String dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }
}
