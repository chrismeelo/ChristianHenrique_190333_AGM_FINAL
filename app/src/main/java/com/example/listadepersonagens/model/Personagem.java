package com.example.listadepersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Personagem implements Serializable {

    // Variaveis contendo as informações do personagem criado

    private String nome;
    private String altura;
    private String nascimento;
    private String telefone;
    private String endereco;
    private String CEP;
    private String genero;
    private String RG;


    private int id = 0;

    // Classe personagem que está utilizando as variaveis que criei como parametro

    public Personagem(String nome, String altura, String nascimento, String telefone, String endereco, String CEP,String genero,String RG) {

        // Dando referencia ao conteudo das variaveis criadas

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.telefone = telefone;
        this.endereco = endereco;
        this.CEP = CEP;
        this.genero = genero;
        this.RG = RG;
    }

    // Classe personagem vazia utilizada no formulario

    public Personagem() {
    }

    // Setando e Gets

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCEP() {
        return CEP;
    }

    public void setCEP(String CEP) {
        this.CEP = CEP;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getRG() {
        return RG;
    }

    public void setRG(String RG) {
        this.RG = RG;
    }

    @NonNull
    @Override

    public String toString() {
        return nome;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public boolean IdValido() {
        return id > 0;
    }
}

