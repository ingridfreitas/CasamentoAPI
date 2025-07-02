package br.com.ingrid.CasamentoAPI.resource;

import br.com.ingrid.CasamentoAPI.model.Convidados;
import br.com.ingrid.CasamentoAPI.repository.ConvidadosRepository;
import br.com.ingrid.CasamentoAPI.service.ConvidadosService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/convidados")
public class ConvidadosResource {
    @Autowired
    private ConvidadosService convidadosService;

    @Autowired
    private ConvidadosRepository convidadosRepository;

    @CrossOrigin()
    @GetMapping
    public List<Convidados> listarConvidados(){
        return convidadosService.listarConvidados();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    public ResponseEntity<Convidados> buscarConvidadoId(@PathVariable Integer id){
        return convidadosService.buscarPorId(id);
    }

    @CrossOrigin()
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerConvidado(@PathVariable Integer id) {
        return convidadosService.removerConvidado(id);
    }

    @CrossOrigin()
    @GetMapping("/nome")
    public ResponseEntity<List<Convidados>> buscarPorNome(@RequestParam String nome){
        return new ResponseEntity<List<Convidados>>(convidadosRepository.findByNome(nome), HttpStatus.OK);
    }

    @CrossOrigin()
    @GetMapping("/codigo")
    public ResponseEntity<List<Convidados>> buscarPorCodigo(@RequestParam String codigo){
        return new ResponseEntity<List<Convidados>>(convidadosRepository.findByCodigo(codigo), HttpStatus.OK);
    }

    @CrossOrigin()
    @GetMapping("/presenca")
    public ResponseEntity<List<Convidados>> buscarPorPresenca(@RequestParam String presenca){
        return new ResponseEntity<List<Convidados>>(convidadosRepository.findByPresenca(presenca), HttpStatus.OK);
    }

    @CrossOrigin()
    @PostMapping(value = {"/add"})
    public ResponseEntity<Convidados> criar(@RequestBody Convidados convidados, HttpServletResponse response){
        Convidados convidadosSalvo = convidadosService.salvar(convidados);
        return ResponseEntity.status(HttpStatus.CREATED).body(convidadosSalvo);
    }

    @CrossOrigin()
    @PutMapping("/{id}")
    public ResponseEntity<Convidados> atualizarConvidado(@PathVariable Integer id, @RequestBody Convidados convidados){
        Convidados convidadoSalvo = convidadosService.atualizarConvidado(id, convidados);
        return ResponseEntity.ok(convidadoSalvo);
    }
}
