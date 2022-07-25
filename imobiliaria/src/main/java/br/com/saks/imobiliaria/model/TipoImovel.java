
package br.com.saks.imobiliaria.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;


@Data
@Entity
public class TipoImovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, length=50)
    private String nome;
    @OneToMany(mappedBy = "tipoimovel")
    private List<Imovel>imoveis;
}
