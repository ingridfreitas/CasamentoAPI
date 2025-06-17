package br.com.ingrid.CasamentoAPI.service;

import br.com.ingrid.CasamentoAPI.model.Recados;
import br.com.ingrid.CasamentoAPI.repository.RecadosRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
public class RecadosService {
    @Autowired()
    private RecadosRepository recadosRepository;

    @CrossOrigin()
    public List<Recados> listarRecados() {
        return recadosRepository.findAll();
    }

    public Recados salvarRecado(Recados recado){
        return recadosRepository.save(recado);
    }

    public Recados atualizarRecado(Integer id, Recados recado){
        Recados recadoSalvo = buscarRecadoPorId(id);
        recadoSalvo.setNome(recado.getNome());
        recadoSalvo.setMensagem(recado.getMensagem());
        recadoSalvo.setData(recado.getData());
        return recadosRepository.save(recadoSalvo);
    }

    private Recados buscarRecadoPorId(Integer id) {
        return recadosRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Recado com ID " + id + " não encontrado."));
    }
}
