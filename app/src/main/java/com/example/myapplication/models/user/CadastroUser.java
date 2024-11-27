package com.example.myapplication.models.user;

public class CadastroUser {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String birthdate;

    // Novos atributos
    private float naf; // Nível de Atividade Física
    private int idade;  // Idade
    private float peso; // Peso
    private Sexo sexo; // Sexo
    private float altura; // Altura

    public CadastroUser() {
    }

    public CadastroUser(String name, String password, String email, String birthdate, float naf, int idade, float peso, Sexo sexo, float altura) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.birthdate = birthdate;
        this.naf = naf;
        this.idade = idade;
        this.peso = peso;
        this.sexo = sexo;
        this.altura = altura;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public float getNaf() {
        return naf;
    }

    public void setNaf(float naf) {
        this.naf = naf;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }
}