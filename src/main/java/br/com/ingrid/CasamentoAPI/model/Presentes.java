package br.com.ingrid.CasamentoAPI.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "presentes")
public class Presentes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome_presente;
    private String descricao;
    private Float valor;
    private String parcelas;
    private String link;
    private String imagem;

    /* GETTER AND SETTER */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome_presente() {
        return nome_presente;
    }

    public void setNome_presente(String nome_presente) {
        this.nome_presente = nome_presente;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Float getValor() {
        return valor;
    }

    public void setValor(Float valor) {
        this.valor = valor;
    }

    public String getParcelas() {
        return parcelas;
    }

    public void setParcelas(String parcelas) {
        this.parcelas = parcelas;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    /* Equals and Hashcode */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Presentes presentes = (Presentes) o;
        return Objects.equals(id, presentes.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /* Constructors */

    public Presentes() {
    }

    public Presentes(Integer id, String imagem, String link, String parcelas, Float valor, String descricao, String nome_presente) {
        this.id = id;
        this.imagem = imagem;
        this.link = link;
        this.parcelas = parcelas;
        this.valor = valor;
        this.descricao = descricao;
        this.nome_presente = nome_presente;
    }
}
