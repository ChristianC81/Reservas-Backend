/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.service;

import com.tapgroup.pwsalonreservas.model.Persona;
import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.repository.PersonaRepository;

import com.tapgroup.pwsalonreservas.service.dao.PersonaServiceDao;
import com.tapgroup.pwsalonreservas.service.dao.UsuarioServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author chris
 */
@Service
public class PersonaServiceImpl implements PersonaServiceDao {

    @Autowired
    PersonaRepository personaRepository;

    @Override
    public ResponseEntity<Persona> postPerson(Persona person) {
        if (!personaRepository.existsByDniPasaporte(person.getDniPasaporte())) {
            Persona newPerson = new Persona();
            newPerson.setDniPasaporte(person.getDniPasaporte());
            newPerson.setNombre(person.getNombre());
            newPerson.setApellido(person.getApellido());
            newPerson.setFechaNac(person.getFechaNac());
            newPerson.setTelefono(person.getTelefono());
            newPerson.setCelular(person.getCelular());
            return new ResponseEntity<>(personaRepository.save(person), HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
    }
}

