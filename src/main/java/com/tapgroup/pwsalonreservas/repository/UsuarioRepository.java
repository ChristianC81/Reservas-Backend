/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Usuario;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author chris
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Boolean existsByEmailOrNombreUsuario(String email, String nombreUsuario);

    Boolean existsByEmail(String email);

    Boolean existsByNombreUsuario(String nombreUsuario);

    Usuario findByEmail(String email);

    Usuario findByIdUsuario(int id);
    List<Usuario> findByEstado(Boolean estado);

    int countByEstadoAndRolIdRolNot(Boolean estado,Integer rol);
   
    List<Usuario> findByEstadoAndRolIdRolNot(Boolean estado,Integer rol);
    
    Usuario findByIdUsuario(Integer idUsu);
    List<Usuario> findAll();

    Usuario findByNombreUsuario(String nombreUsuario);
}
