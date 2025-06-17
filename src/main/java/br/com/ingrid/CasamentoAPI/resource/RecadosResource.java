package br.com.ingrid.CasamentoAPI.resource;

import br.com.ingrid.CasamentoAPI.model.ImageModel;
import br.com.ingrid.CasamentoAPI.model.Recados;
import br.com.ingrid.CasamentoAPI.repository.RecadosRepository;
import br.com.ingrid.CasamentoAPI.service.RecadosService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/recados")
public class RecadosResource {

    @Autowired()
    private RecadosService recadosService;

    @Autowired()
    private RecadosRepository recadosRepository;

    @CrossOrigin()
    @GetMapping
    public List<Recados> listarRecados(){
        return recadosService.listarRecados();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Recados> buscarRecadoId(@PathVariable Integer id){
        Optional<Recados> recado = recadosRepository.findById(id);
        return recado.isPresent() ? ResponseEntity.ok(recado.get()) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> removerRecado(@PathVariable Integer id) {
        Optional<Recados> recado = recadosRepository.findById(id);
        if (recado.isPresent()) {
            recadosRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    public Set<ImageModel> upload(MultipartFile[] files) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();
        for (MultipartFile file: files){
            ImageModel image = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(image);
        }

        return imageModels;
    }

    @CrossOrigin()
    @PostMapping(value = {"/add"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> addRecadoComImagem(@RequestPart("recado") Recados recado, @RequestPart(value = "imagem", required = false) MultipartFile[] file) {
        try {
            if (recado.getNome() == null || recado.getNome().trim().isEmpty() ||
                    recado.getMensagem() == null || recado.getMensagem().trim().isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("{\"message\": \"Nome e mensagem são obrigatórios.\"}");
            }
            Set<ImageModel> images = (file != null && file.length > 0) ? upload(file) : new HashSet<>();

            recado.setRecadosImages(images);
            Recados savedRecado = recadosService.salvarRecado(recado);

            return new ResponseEntity<>(savedRecado, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(
                    "{\"message\": \"Erro interno do servidor ao salvar o recado: " + e.getMessage() + "\"}",
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }


    @PutMapping("/{id}")
    public ResponseEntity<Recados> atualizarRecado(@PathVariable Integer id, @RequestBody Recados recado){
        Recados recadoSalvo = recadosService.atualizarRecado(id, recado);
        return ResponseEntity.ok(recadoSalvo);
    }

}
