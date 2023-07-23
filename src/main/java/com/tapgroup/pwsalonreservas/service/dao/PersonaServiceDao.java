package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.Persona;
import org.springframework.http.ResponseEntity;

public interface PersonaServiceDao {

    ResponseEntity<?> postPerson (Persona person);
}
