package br.com.ingrid.CasamentoAPI.model;

import jakarta.persistence.*;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "recados")
public class Recados {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nome;

    @Lob
    private String mensagem;

    private String data;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "recado_id") // Cria a FK na tabela de imagem
    private Set<ImageModel> recadosImages;



    /* Getters and Setters */
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public Set<ImageModel> getRecadosImages() {
        return recadosImages;
    }

    public void setRecadosImages(Set<ImageModel> recadosImages) {
        this.recadosImages = recadosImages;
    }

    /* Constructor */

    public Recados() {
    }

    public Recados(Integer id, String nome, String mensagem, String data, Set<ImageModel> recadosImages) {
        this.id = id;
        this.nome = nome;
        this.mensagem = mensagem;
        this.data = data;
        this.recadosImages = recadosImages;
    }

    /* Equals and Hashcode */

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Recados recados = (Recados) o;
        return Objects.equals(id, recados.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
