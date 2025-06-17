package br.com.ingrid.CasamentoAPI.repository;

import br.com.ingrid.CasamentoAPI.model.Recados;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecadosRepository extends JpaRepository<Recados, Integer> {
}
