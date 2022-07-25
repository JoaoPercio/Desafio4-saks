
package br.com.saks.imobiliaria.controller;

import br.com.saks.imobiliaria.model.TipoImovel;
import br.com.saks.imobiliaria.repository.TipoImovelRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tipoimovel")
public class TipoImovelController {
    @Autowired
    private TipoImovelRepository tipoImovelRepository;
@GetMapping
    public List<TipoImovel> listarTodos() {
        return tipoImovelRepository.findAll();
    }    
@GetMapping(value="/{id}")
    public TipoImovel listarPeloId(@PathVariable Long id) {
        Optional<TipoImovel> tipoImovelResponse = tipoImovelRepository.findById(id);
        TipoImovel tipoImovel = tipoImovelResponse.get();
        return tipoImovel;
    }
@PostMapping
    public TipoImovel adicionar(@RequestBody TipoImovel tipoimovel) {
        return tipoImovelRepository.save(tipoimovel);
    }
@PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody TipoImovel tipoimovel) {
        return tipoImovelRepository.findById(id)
                .map(record -> {
                    record.setNome(tipoimovel.getNome());
                    TipoImovel tipoImovelUpdated = tipoImovelRepository.save(record);
                    return ResponseEntity.ok().body(tipoImovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return tipoImovelRepository.findById(id)
                .map(record -> {
                    tipoImovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }    
}

