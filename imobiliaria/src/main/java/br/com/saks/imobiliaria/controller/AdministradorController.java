
package br.com.saks.imobiliaria.controller;


import br.com.saks.imobiliaria.model.Administrador;
import br.com.saks.imobiliaria.repository.AdministradorRepository;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
@RequestMapping("/administrador")
public class AdministradorController {
    @Autowired
    private AdministradorRepository administradorRepository;
    
    @GetMapping
    public List<Administrador> listarTodos() {
        return administradorRepository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Optional<Administrador> listarPeloId(@PathVariable Long id) {
        return administradorRepository.findById(id);
    }
    
    @PostMapping
    public Administrador adicionar(@RequestBody Administrador administrador) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      MessageDigest md= MessageDigest.getInstance("SHA-256");
             byte MessageDigest[]=md.digest(administrador.getSenha().getBytes("UTF-8"));
            
            StringBuilder sb= new StringBuilder();
            for(byte b: MessageDigest){
                sb.append(String.format("%02X", 0xFF & b));
                
                
            }
              String SenhaHex=sb.toString();
             administrador.setSenha(SenhaHex);
        return administradorRepository.save(administrador);
    }
    
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Administrador administrador)throws NoSuchAlgorithmException, UnsupportedEncodingException {
       MessageDigest md;
        
                md = MessageDigest.getInstance("SHA-256");
                byte MessageDigest[]=md.digest(administrador.getSenha().getBytes("UTF-8"));
                 
            StringBuilder sb= new StringBuilder();
            for(byte b: MessageDigest){
                sb.append(String.format("%02X", 0xFF & b));
                
                
            }
             String SenhaHex=sb.toString();
        return administradorRepository.findById(id)
                .map(record -> {
                    record.setNome(administrador.getNome());
                    record.setEmail(administrador.getEmail());
                    record.setSenha(SenhaHex);
          
             Administrador administradorUpdated = administradorRepository.save(record);
                    return ResponseEntity.ok().body(administradorUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }
    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return administradorRepository.findById(id)
                .map(record-> {
                    administradorRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }  
}
