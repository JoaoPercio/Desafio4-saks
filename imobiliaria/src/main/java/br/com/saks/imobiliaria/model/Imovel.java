
package br.com.saks.imobiliaria.model;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import lombok.Data;

@Data
@Entity
public class Imovel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, name = "id_tipo_imovel")
    private Long idTipoImovel;
    @Transient
    TipoImovel tipoImovel;
    @Column(nullable = false, length=100)
    private String titulo;
    @Column(length=500)
    private String descricao;
    @Temporal(TemporalType.DATE)
    @Column(nullable = false, name = "data_criacao")
    private Date dataCriacao;
    @Column(precision=8,scale=2)
    private double valor;
    @Column(nullable = false, length=1)
    private int status; 
}
