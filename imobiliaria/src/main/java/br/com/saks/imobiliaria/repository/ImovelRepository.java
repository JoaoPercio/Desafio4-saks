
package br.com.saks.imobiliaria.repository;


import br.com.saks.imobiliaria.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImovelRepository extends JpaRepository<Imovel,Long> {
    
}
