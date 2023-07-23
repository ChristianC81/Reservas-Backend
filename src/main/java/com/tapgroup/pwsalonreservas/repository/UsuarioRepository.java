/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Usuario;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author chris
 */
@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    //!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!
    //al mandar un parametro siempre va los :varible
    //los : y la variable si no no vale
    //buscar el si existe un usuario
//    @Query("SELECT COUNT(u)>0 FROM Usuario u WHERE u.nombreUsuario = :usuario")
//    boolean validarUsuario(@Param("usuario") String usuNombre);
//
//    @Query("SELECT u FROM Usuario u WHERE u.estado = 'Activo'")
//    List<Usuario> getUsuariosActivos();
//
//    @Query("SELECT u FROM Usuario u WHERE u.estado = 'Inactivo'")
//    List<Usuario> getUsuariosInactivos();

//    @Query("SELECT u FROM Usuario u JOIN u.personaByIdPersona p WHERE p.email = :correo")
//    Optional<Usuario> findByUsuario(@Param("correo") String persEmail);

//    @Query("SELECT u FROM Usuario u")
//    List<Usuario> getUsuarios();

    Boolean existsByEmailOrNombreUsuario(String email, String nombreUsuario);

    Boolean existsByEmail(String email);

    Boolean existsByNombreUsuario(String nombreUsuario);

    Usuario findByEmail(String email);

}
