package br.com.ingrid.CasamentoAPI.repository;

import br.com.ingrid.CasamentoAPI.model.Presentes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PresentesRepository extends JpaRepository<Presentes, Integer> {
}
