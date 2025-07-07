package br.com.ingrid.CasamentoAPI.service;

import br.com.ingrid.CasamentoAPI.model.Presentes;
import br.com.ingrid.CasamentoAPI.repository.PresentesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class PresentesService {
    @Autowired
    private PresentesRepository presentesRepository;

    @CrossOrigin()
    public List<Presentes> listarPresentes(){
        return presentesRepository.findAll();
    }

    public Presentes salvarPresentes(Presentes presentes){
        return presentesRepository.save(presentes);
    }

    public ResponseEntity<Presentes> buscarPorId(@PathVariable Integer id) {
        Optional<Presentes> presentes = presentesRepository.findById(id);
        return presentes.isPresent() ? ResponseEntity.ok(presentes.get()) : ResponseEntity.notFound().build();
    }

    private Presentes buscarPresenteId(Integer id) {
        return presentesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Convidado com ID " + id + " não encontrado."));
    }

    public ResponseEntity<?> removerPresente(@PathVariable Integer id) {
        Optional<Presentes> presentes = presentesRepository.findById(id);
        if (presentes.isPresent()) {
            presentesRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Presentes atualizarPresente(Integer id, Presentes presentes){
        Presentes presenteSalvo = buscarPresenteId(id);
        presenteSalvo.setNome_presente(presentes.getNome_presente());
        presenteSalvo.setDescricao(presentes.getDescricao());
        presenteSalvo.setValor(presentes.getValor());
        presenteSalvo.setParcelas(presentes.getParcelas());
        presenteSalvo.setLink(presentes.getLink());
        presenteSalvo.setImagem(presentes.getImagem());
        return presentesRepository.save(presenteSalvo);
    }

}
