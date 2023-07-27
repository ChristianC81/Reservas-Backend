/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.controller;

import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.service.UsuarioServiceImpl;
import com.tapgroup.pwsalonreservas.service.dao.UsuarioServiceDao;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jonny
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    UsuarioServiceImpl usuarioService;

    @Autowired
    UsuarioServiceDao usuarioServiceDao;


    @GetMapping("/checkAvailableEmail/{email}")
    public ResponseEntity<Boolean> checkAvailableEmail(@PathVariable String email) {
        return usuarioServiceDao.checkAvailableEmail(email);
    }

    @GetMapping("/checkAvailableUsername/{username}")
    public ResponseEntity<Boolean> checkAvailableUsername(@PathVariable String username) {
        return usuarioServiceDao.checkAvailableUsername(username);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtenerUsuarioPorId(@PathVariable int id) {
        return usuarioService.usuarioEncontrado(id);
    }

    @GetMapping("email/{email}")
    public ResponseEntity<?> obtenerUsuarioPorEmail(@PathVariable String email) {
        return usuarioServiceDao.usuarioEncontradoEmail(email);
    }

    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> obtenerTodosLosUsuarios() {
        return usuarioService.todosUsuarios();
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestParam(value = "idPersona") Integer idPersona, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return usuarioServiceDao.postUser(idPersona, username, password, email);
    }
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<?> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario u) {
        return usuarioServiceDao.putUser(id, u);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        return usuarioServiceDao.deleteUser(id);
    }
    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        return usuarioServiceDao.logIn(usuario);
    }

    //Metodo listar usuarios activos
    @GetMapping("/activos")
    public ResponseEntity<List<Usuario>> listaUsuarios() {
        ResponseEntity<List<Usuario>> response = usuarioServiceDao.usuariosActivos();
        return response;
    }

    // Método listar usuarios inactivos
    @GetMapping("/inactivos")
    public ResponseEntity<List<Usuario>> listaUsuariosInactivos() {
        ResponseEntity<List<Usuario>> response = usuarioServiceDao.usuariosInactivos();
        return response;
    }

    //Metodo para actualizar estado
    @PutMapping("/actualizarest/{id}")
    public ResponseEntity<Usuario> actualizarEstadoUsuario(@PathVariable Integer id, @RequestBody Usuario u) {
        ResponseEntity<Usuario> response = usuarioServiceDao.cambiarEstado(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            Usuario usuarioActualizado = response.getBody();
            usuarioActualizado.setEstado(u.getEstado());

            try {
                Usuario usuarioGuardado = usuarioServiceDao.save(usuarioActualizado);
                return new ResponseEntity<>(usuarioGuardado, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(response.getStatusCode());
        }
    }
    @PostMapping("/countUs")
    public ResponseEntity<Integer> countUs(@RequestBody Boolean estado) {

        return usuarioServiceDao.userCount(estado);

    }

    @PostMapping("/userState")
    public ResponseEntity<List<Usuario>> userState(@RequestBody boolean estado){
        return usuarioServiceDao.userState(estado);
    }

    @PostMapping("/userUpdateState")
    public ResponseEntity<Usuario>userUpdateState(@RequestBody Usuario usu){

        return usuarioServiceDao.userUpdateState(usu);
    }

}
