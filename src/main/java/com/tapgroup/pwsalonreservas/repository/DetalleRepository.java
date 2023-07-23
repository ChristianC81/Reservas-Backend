/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Detalle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author jonny
 */
@Repository
public interface DetalleRepository extends JpaRepository<Detalle, Integer> {

    @Query(value = "Select * from detalle u where u.deId = :deId", nativeQuery = true)
    public Detalle buscarDetalle(int deId);

}
