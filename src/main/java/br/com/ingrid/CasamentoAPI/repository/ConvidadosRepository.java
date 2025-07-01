package br.com.ingrid.CasamentoAPI.repository;

import br.com.ingrid.CasamentoAPI.model.Convidados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConvidadosRepository extends JpaRepository<Convidados, Integer> {
    List<Convidados> findByNome(String nome);
    List<Convidados> findByCodigo(String codigo);
}
