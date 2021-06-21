package com.example.gerenciadordelivros.dominio;

import java.io.Serializable;
import java.util.List;

public class LivroAutor implements Serializable {

    public static List<LivroAutor> listAll;
    private Long id;
    private String titulo;
    private String autor;

    public LivroAutor(Long id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public LivroAutor(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    public LivroAutor() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return "Livro{" + "id=" + id + ", titulo=" + titulo + ", autor=" + autor + '}';
    }
}
