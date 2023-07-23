/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tapgroup.pwsalonreservas.repository;


import com.tapgroup.pwsalonreservas.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author chris
 */
@Repository
public interface RolRepository extends JpaRepository<Rol, Integer>{
        @Query(value = "Select * from rol u where u.nombre = :nombre", nativeQuery = true)
    public Rol buscarRol(String nombre);
}
