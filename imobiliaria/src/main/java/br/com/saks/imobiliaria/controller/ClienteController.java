
package br.com.saks.imobiliaria.controller;
import br.com.saks.imobiliaria.model.Cliente;
import br.com.saks.imobiliaria.repository.ClienteRepository;
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
@RequestMapping("/cliente")
public class ClienteController {
    @Autowired
    private ClienteRepository clienteRepository;
    
    
    
    @GetMapping
    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }
    
    @GetMapping(value="/{id}")
    public Cliente listarPeloId(@PathVariable Long id) {
        Optional<Cliente> clienteResponse = clienteRepository.findById(id);
        Cliente cliente = clienteResponse.get();
        return cliente;
    }
   
    @PostMapping
    public Cliente adicionar(@RequestBody Cliente cliente) throws NoSuchAlgorithmException, UnsupportedEncodingException {
      MessageDigest md= MessageDigest.getInstance("SHA-256");
             byte MessageDigest[]=md.digest(cliente.getSenha().getBytes("UTF-8"));
            
            StringBuilder sb= new StringBuilder();
            for(byte b: MessageDigest){
                sb.append(String.format("%02X", 0xFF & b));
               }
             String SenhaHex=sb.toString();
            cliente.setSenha(SenhaHex);
        return clienteRepository.save(cliente);
    }
    @PutMapping(value="/{id}")
    public ResponseEntity editar(@PathVariable Long id, @RequestBody Cliente cliente)throws NoSuchAlgorithmException, UnsupportedEncodingException {
         MessageDigest md;
        
                md = MessageDigest.getInstance("SHA-256");
                byte MessageDigest[]=md.digest(cliente.getSenha().getBytes("UTF-8"));
                 
            StringBuilder sb= new StringBuilder();
            for(byte b: MessageDigest){
                sb.append(String.format("%02X", 0xFF & b));
                
                
            }
             String SenhaHex=sb.toString();

        return clienteRepository.findById(id)
                .map(record -> {
                    record.setNome(cliente.getNome());
                    record.setTelefone(cliente.getTelefone());
                    record.setEmail(cliente.getEmail());
                    record.setSenha(SenhaHex);
           
            
             Cliente clienteUpdated = clienteRepository.save(record);
                    return ResponseEntity.ok().body(clienteUpdated);
                }).orElse(ResponseEntity.notFound().build());
    }    
    @DeleteMapping(value="/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(record -> {
                    clienteRepository.deleteById(id);
                    return ResponseEntity.ok().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
