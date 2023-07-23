/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Complemento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface ComplementoRepository extends JpaRepository<Complemento, Integer> {

    @Query(value = "Select * from complemento u where u.nombre = :nombre", nativeQuery = true)
    public Complemento buscarComplemento(String nombre);

    //Consulta para obtener los nombres de los complementos
    @Query(value = "SELECT nombre FROM complemento WHERE id_grupocomplemento = (SELECT MAX(id_grupocomplemento) FROM grupo_complemento)", nativeQuery = true)
    List<String> obtenerNombresComplemento();

}
