
package br.com.saks.imobiliaria.repository;


import br.com.saks.imobiliaria.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Long>{
    
}
