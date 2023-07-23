package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.Usuario;
import org.springframework.http.ResponseEntity;

public interface UsuarioServiceDao {

    ResponseEntity<Boolean> checkAvailableEmail(String email);

    ResponseEntity<Boolean> checkAvailableUsername(String username);

    ResponseEntity<?> postUser(Integer idPersona, String username, String password, String email);

    ResponseEntity<Usuario> logIn(Usuario usuario);
}
