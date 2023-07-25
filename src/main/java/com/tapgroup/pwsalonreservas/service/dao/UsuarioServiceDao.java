package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.Usuario;
import java.util.List;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UsuarioServiceDao {

    ResponseEntity<Boolean> checkAvailableEmail(String email);

    ResponseEntity<Boolean> checkAvailableUsername(String username);

    ResponseEntity<?> postUser(Integer idPersona, String username, String password, String email);

    ResponseEntity<Usuario> logIn(Usuario usuario);


    Usuario save(Usuario usuario);
    
    ResponseEntity<List<Usuario>> usuariosActivos();

    ResponseEntity<List<Usuario>> usuariosInactivos();

    ResponseEntity<Usuario> cambiarEstado(int id);
    
    ResponseEntity<Integer> userCount(Boolean estado);
    
    ResponseEntity<List<Usuario>> userState(boolean estado);
    
    ResponseEntity<Usuario> userUpdateState(Usuario usu);

}
