/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.service;


import com.tapgroup.pwsalonreservas.model.Persona;
import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.repository.PersonaRepository;
import com.tapgroup.pwsalonreservas.repository.RolRepository;
import com.tapgroup.pwsalonreservas.repository.UsuarioRepository;

import java.util.List;
import java.util.Optional;

import com.tapgroup.pwsalonreservas.service.dao.UsuarioServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author chris
 */
@Service
public class UsuarioServiceImpl implements UsuarioServiceDao {


    private static final Integer idRolAdmin = 1;
    private static final Integer idRolCliente = 2;

    @Autowired
    PersonaRepository personaRepository;

    @Autowired
    RolRepository rolRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    public ResponseEntity<Boolean> checkAvailableEmail(String email) {
        return new ResponseEntity<>(!usuarioRepository.existsByEmail(email), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Boolean> checkAvailableUsername(String username) {
        return new ResponseEntity<>(!usuarioRepository.existsByNombreUsuario(username), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Usuario> usuarioEncontrado(int id) {
        return new ResponseEntity<>(usuarioRepository.findByIdUsuario(id),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Usuario> usuarioEncontradoEmail(String email) {
        return new ResponseEntity<>(usuarioRepository.findByEmail(email),HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Usuario>> todosUsuarios() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> postUser(Integer idPersona, String username, String password, String email) {
        // VALIDAR EXISTENCIA DE PERSONA
        Persona persona = personaRepository.findById(idPersona).orElse(null);
        if (null == persona) {
            return new ResponseEntity<>("No existe la persona", HttpStatus.NOT_FOUND);
        }

        // VALIDAR QUE EL EMAIL NO ESTE EN USO
        if (usuarioRepository.existsByEmailOrNombreUsuario(email, username)) {
            return new ResponseEntity<>("El email o nombre de usuario ya esta en uso.", HttpStatus.CONFLICT);
        }

        // CREAR USUARIO
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode(password);
        Usuario usuario = new Usuario();
        usuario.setEstado(true);
        usuario.setNombreUsuario(username);
        usuario.setContrasenia(hashedPassword);
        usuario.setEmail(email);
        usuario.setPersona(persona);
        usuario.setRol(rolRepository.findById(idRolCliente).orElse(null));
        usuarioRepository.save(usuario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> putUser(Integer idPersona, Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idPersona);
        if (!optionalUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuario.setIdUsuario(idPersona);
        usuarioRepository.save(usuario);
        return ResponseEntity.ok(usuario);
    }
    @Override
    public ResponseEntity<?> deleteUser(Integer idPersona) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(idPersona);
        if (!optionalUsuario.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        usuarioRepository.deleteById(idPersona);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Usuario> logIn(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Usuario usu = usuarioRepository.findByEmail(usuario.getEmail());

        if (usu != null) {
            if (passwordEncoder.matches(usuario.getContrasenia(), usu.getContrasenia())) {
                //todo okey retorna el usuario
                return new ResponseEntity<>(usu, HttpStatus.OK);
            } else {
                //contraseña mal
                return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
            }
        } else {
            //usuario mal
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    // Metodo listar usuarios activos
    @Override
    public ResponseEntity<List<Usuario>> usuariosActivos() {
        List<Usuario> usuariosActivos = usuarioRepository.findByEstado(true);
        return new ResponseEntity<>(usuariosActivos, HttpStatus.OK);
    }

    // Metodo listar usuarios inactivos
    @Override
    public ResponseEntity<List<Usuario>> usuariosInactivos() {
        List<Usuario> usuariosInactivos = usuarioRepository.findByEstado(false);
        return new ResponseEntity<>(usuariosInactivos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Usuario> cambiarEstado(int id) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setEstado(!usuario.getEstado()); // Cambiar el estado del usuario
            usuarioRepository.save(usuario); // Guardar el usuario actualizado en la base de datos
            return new ResponseEntity<>(usuario, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Override
    public ResponseEntity<Integer> userCount(Boolean estado) {
       //1 porque no va a seleccionar los admins
        Integer usuCount = usuarioRepository.countByEstadoAndRolIdRolNot(estado, 1);
        System.out.println(usuCount +" usuaarios"+estado);
        return new ResponseEntity<>(usuCount, HttpStatus.OK);
    }

    //recuperar el usuario por estados 
    @Override
    public ResponseEntity<List<Usuario>> userState(boolean estado) {
        List<Usuario> usu = usuarioRepository.findByEstadoAndRolIdRolNot(estado, 1);
        return new ResponseEntity<>(usu, HttpStatus.OK);
    }

    //activar o desactivar los usuarios
    @Override
    public ResponseEntity<Usuario> userUpdateState(Usuario usu) {

        Usuario userUp = usuarioRepository.findByIdUsuario(usu.getIdUsuario());
        if (userUp != null) {

            userUp.setEstado(usu.getEstado());
            usuarioRepository.save(userUp);
            return new ResponseEntity<>(usu, HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
}

