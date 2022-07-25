/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package br.com.saks.imobiliaria.repository;


import br.com.saks.imobiliaria.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ImovelRepository extends JpaRepository<Imovel,Long> {
    
}
