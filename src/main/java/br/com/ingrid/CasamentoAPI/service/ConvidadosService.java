package br.com.ingrid.CasamentoAPI.service;

import br.com.ingrid.CasamentoAPI.model.Convidados;
import br.com.ingrid.CasamentoAPI.repository.ConvidadosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Service
public class ConvidadosService {
    @Autowired
    private ConvidadosRepository convidadosRepository;

    @CrossOrigin()
    public List<Convidados> listarConvidados() {
        return convidadosRepository.findAll();
    }

    public Convidados salvarConvidado(Convidados convidado){
        return convidadosRepository.save(convidado);
    }

    public Convidados atualizarStatus(Integer id, Convidados convidados){
        Convidados convidadoSalvo = buscarConvidadoId(id);
        convidadoSalvo.setCodigo(convidados.getCodigo());
        convidadoSalvo.setNome(convidados.getNome());
        convidadoSalvo.setData(convidados.getData());
        convidadoSalvo.setPresenca(convidados.getPresenca());
        convidadoSalvo.setContato(convidados.getContato());
        return convidadosRepository.save(convidadoSalvo);
    }

    private Convidados buscarConvidadoId(Integer id) {
        return convidadosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Convidado com ID " + id + " não encontrado."));
    }

    public ResponseEntity<Convidados> buscarPorId(@PathVariable Integer id) {
        Optional<Convidados> convidados = convidadosRepository.findById(id);
        return convidados.isPresent() ? ResponseEntity.ok(convidados.get()) : ResponseEntity.notFound().build();
    }

    public ResponseEntity<?> removerConvidado(@PathVariable Integer id) {
        Optional<Convidados> convidados = convidadosRepository.findById(id);
        if (convidados.isPresent()) {
            convidadosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Convidados salvar(Convidados convidados) {
        return convidadosRepository.save(convidados);
    }

    public Convidados atualizarConvidado(Integer id, Convidados convidados){
        Convidados convidadoSalvo = buscarConvidadoId(id);
        convidadoSalvo.setCodigo(convidados.getCodigo());
        convidadoSalvo.setNome(convidados.getNome());
        convidadoSalvo.setData(convidados.getData());
        convidadoSalvo.setPresenca(convidados.getPresenca());
        convidadoSalvo.setContato(convidados.getContato());
        return convidadosRepository.save(convidadoSalvo);
    }
}
