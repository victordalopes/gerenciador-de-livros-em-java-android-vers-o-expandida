package com.example.gerenciadordelivros.dominio;

import java.io.Serializable;

public class Editora implements Serializable {

    private Long id;
    private String nome;
    private String fundacao;
    private String sede;
    private String cnpj;

    public Editora(Long id, String nome, String fundacao, String sede, String cnpj) {
        this.id = id;
        this.nome = nome;
        this.fundacao = fundacao;
        this.sede = sede;
        this.cnpj = cnpj;
    }

    public Editora(String nome, String fundacao, String sede, String cnpj) {
        this.nome = nome;
        this.fundacao = fundacao;
        this.sede = sede;
        this.cnpj = cnpj;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFundacao() {
        return fundacao;
    }

    public void setFundacao(String fundacao) {
        this.fundacao = fundacao;
    }

    public String getSede() {
        return sede;
    }

    public void setSede(String sede) {
        this.sede = sede;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.sede = cnpj;
    }

    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", nome=" + nome + ", fundacao=" + fundacao + ", sede=" + sede + ", cnpj=" + cnpj + '}';
    }
}
