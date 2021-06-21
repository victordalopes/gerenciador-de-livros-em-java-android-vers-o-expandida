package com.example.gerenciadordelivros.dominio;

import java.io.Serializable;

public class Autor implements Serializable {

    private Long id;
    private String nome;
    private String idade;
    private String editora;
    private String genero;

    public Autor(Long id, String nome, String idade, String editora, String genero) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.editora = editora;
        this.genero = genero;
    }

    public Autor(String nome, String idade, String editora, String genero) {
        this.nome = nome;
        this.idade = idade;
        this.editora = editora;
        this.genero = genero;
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

    public String getIdade() {
        return idade;
    }

    public void setIdade(String idade) {
        this.idade = idade;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nome=" + nome + ", idade=" + idade + ", editora=" + editora + ", genero=" + genero + '}';
    }
}
