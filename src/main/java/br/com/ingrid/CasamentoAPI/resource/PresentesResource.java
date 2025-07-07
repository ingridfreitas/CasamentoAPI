package br.com.ingrid.CasamentoAPI.resource;

import br.com.ingrid.CasamentoAPI.model.Convidados;
import br.com.ingrid.CasamentoAPI.model.Presentes;
import br.com.ingrid.CasamentoAPI.service.PresentesService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/presentes")
public class PresentesResource {
    @Autowired
    private PresentesService presentesService;

    @CrossOrigin()
    @GetMapping
    public List<Presentes> listarPresentes(){
        return presentesService.listarPresentes();
    }

    @CrossOrigin()
    @GetMapping("/{id}")
    public ResponseEntity<Presentes> buscarPresenteId(@PathVariable Integer id){
        return presentesService.buscarPorId(id);
    }

    @CrossOrigin()
    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerConvidado(@PathVariable Integer id) {
        return presentesService.removerPresente(id);
    }

    @CrossOrigin()
    @PostMapping(value = {"/add"})
    public ResponseEntity<Presentes> criar(@RequestBody Presentes presentes, HttpServletResponse response){
        Presentes presentesSalvo = presentesService.salvarPresentes(presentes);
        return ResponseEntity.status(HttpStatus.CREATED).body(presentesSalvo);
    }

    @CrossOrigin()
    @PutMapping("/{id}")
    public ResponseEntity<Presentes> atualizarPresente(@PathVariable Integer id, @RequestBody Presentes presentes){
        Presentes presenteSalvo = presentesService.atualizarPresente(id, presentes);
        return ResponseEntity.ok(presenteSalvo);
    }
}
