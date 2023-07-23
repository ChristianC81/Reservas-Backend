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

//    @Operation(summary = "Se obtiene la lista de Usuarios")
//    @GetMapping("/listar")
//    public ResponseEntity<List<Usuario>> listaUsuarioes() {
//        return new ResponseEntity<>(usuarioService.findByAll(), HttpStatus.OK);
//    }

    @GetMapping("/checkAvailableEmail/{email}")
    public ResponseEntity<Boolean> checkAvailableEmail(@PathVariable String email) {
        return usuarioServiceDao.checkAvailableEmail(email);
    }

    @GetMapping("/checkAvailableUsername/{username}")
    public ResponseEntity<Boolean> checkAvailableUsername(@PathVariable String username) {
        return usuarioServiceDao.checkAvailableUsername(username);
    }

    @PostMapping("/crear")
    public ResponseEntity<?> crearUsuario(@RequestParam(value = "idPersona") Integer idPersona, @RequestParam String username, @RequestParam String password, @RequestParam String email) {
        return usuarioServiceDao.postUser(idPersona, username, password, email);
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody Usuario usuario) {
        return usuarioServiceDao.logIn(usuario);
    }


//    @PutMapping("/actualizar/{id}")
//    public ResponseEntity<Usuario> actualizarUsuario(@PathVariable Integer id, @RequestBody Usuario u) {
//        Usuario usu = usuarioService.findById(id);
//        if (usu != null) {
//            try {
//                usu.setUsuNombre(u.getUsuNombre());
//                usu.setUsuContrasenia(u.getUsuContrasenia());
//                usu.setRol(u.getRol());
//                usu.setUsuEstado(u.getUsuEstado());
//                usu.setPersona(u.getPersona());
//
//                return new ResponseEntity<>(usuarioService.save(usu), HttpStatus.CREATED);
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/eliminar/{id}")
//    public ResponseEntity<Usuario> eliminarUsuario(@PathVariable Integer id) {
//        usuarioService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

//
//    //Metodo listar usuarios activos
//    @GetMapping("/todos")
//    public ResponseEntity<List<Usuario>> listaUsuarios() {
//        List<Usuario> usuariosActivos = usuarioService.usuarios();
//        return new ResponseEntity<>(usuariosActivos, HttpStatus.OK);
//    }
//
//    //Metodo listar usuarios activos
//    @GetMapping("/activos")
//    public ResponseEntity<List<Usuario>> listaUsuariosActivos() {
//        List<Usuario> usuariosActivos = usuarioService.usuariosActivos();
//        return new ResponseEntity<>(usuariosActivos, HttpStatus.OK);
//    }
//
//    //Metodo listar usuarios inactivos
//    @GetMapping("/inactivos")
//    public ResponseEntity<List<Usuario>> listaUsuariosInactivos() {
//        List<Usuario> usuariosInactivos = usuarioService.usuariosInactivos();
//        return new ResponseEntity<>(usuariosInactivos, HttpStatus.OK);
//    }

    //Metodo para actualizar estado

//     @PutMapping("/actualizarest/{id}")
//    public ResponseEntity<Usuario> actualizarEstadoUsuario(@PathVariable Integer id,@RequestBody Usuario u) {
//        Usuario usu = usuarioService.findById(id);
//        if (usu != null) {
//            try {
//                usu.setUsuEstado(u.getUsuEstado());
//                return new ResponseEntity<>(usuarioService.save(usu), HttpStatus.CREATED);
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }


}
