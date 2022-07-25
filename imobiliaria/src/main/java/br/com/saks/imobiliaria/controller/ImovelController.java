/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package br.com.saks.imobiliaria.controller;

import br.com.saks.imobiliaria.model.Imovel;
import br.com.saks.imobiliaria.repository.ImovelRepository;
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
@RequestMapping("/imovel")
public class ImovelController {
     @Autowired
    private ImovelRepository imovelRepository;
    @Autowired
   
    @GetMapping
    public List<Imovel> listarTodos() {
        return imovelRepository.findAll();
    }
     @GetMapping(value="/{id}")
    public Imovel listarPeloId(@PathVariable Long id) {
        Optional<Imovel> imovelResponse = imovelRepository.findById(id);
        Imovel imovel = imovelResponse.get();
        return imovel;
    }
    @PostMapping
    public Imovel adicionar(@RequestBody Imovel imovel) {
        imovel.setStatus(1);
        return imovelRepository.save(imovel);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Imovel imovel) {
        return imovelRepository.findById(id)
                .map(record -> {
                    record.setTitulo(imovel.getTitulo());
                    record.setDescricao(imovel.getDescricao());
                    record.setValor(imovel.getValor());
                    record.setStatus(imovel.getStatus());
                    
                    Imovel imovelUpdated = imovelRepository.save(record);
                    return ResponseEntity.ok().body(imovelUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return imovelRepository.findById(id)
                .map(record -> {
                    imovelRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
