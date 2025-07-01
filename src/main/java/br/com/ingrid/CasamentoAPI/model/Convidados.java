package br.com.ingrid.CasamentoAPI.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "convidados")
public class Convidados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String codigo;
    private String nome;
    private String presenca;
    private String data;
    private String contato;

    /* Getter and Setter */

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPresenca() {
        return presenca;
    }

    public void setPresenca(String presenca) {
        this.presenca = presenca;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    /* Equals and Hashcode */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Convidados that = (Convidados) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    /* Constructors */

    public Convidados() {
    }

    public Convidados(Integer id, String codigo, String presenca, String nome, String data, String contato) {
        this.id = id;
        this.codigo = codigo;
        this.presenca = presenca;
        this.nome = nome;
        this.data = data;
        this.contato = contato;
    }
}
