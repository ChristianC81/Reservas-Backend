package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.Usuario;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioServiceDao {

    ResponseEntity<Boolean> checkAvailableEmail(String email);

    ResponseEntity<Boolean> checkAvailableUsername(String username);

    ResponseEntity<Usuario> usuarioEncontrado(int id);
    //GET
    ResponseEntity<List<Usuario>> todosUsuarios();

    //POST
    ResponseEntity<?> postUser(Integer idPersona, String username, String password, String email);
    //PUT
    ResponseEntity<?> putUser(Integer idPersona, Usuario usuario);
    //DELETE
    ResponseEntity<?> deleteUser(Integer idPersona);
    ResponseEntity<Usuario> logIn(Usuario usuario);

    Usuario save(Usuario usuario);

    ResponseEntity<List<Usuario>> usuariosActivos();

    ResponseEntity<List<Usuario>> usuariosInactivos();

    ResponseEntity<Usuario> cambiarEstado(int id);
}
