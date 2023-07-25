package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.Salon;
import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.model.dto.SalonDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SalonServiceDao {

    ResponseEntity<?> getAllCategories();

    ResponseEntity<?> postSalon(SalonDto salon, String emailPublicador);

    ResponseEntity<?> postImage(byte[] imagen, Integer idSalon);

    ResponseEntity<?> listPosts();

    ResponseEntity<?> listPostsByUser(String email);
    ResponseEntity<List<Salon>> salonesActivos();

    ResponseEntity<List<Salon>> salonesInactivos();

    ResponseEntity<Salon> cambiarEstado(int id);
    Salon save(Salon salon);

    ResponseEntity<?> deleteById(Integer salonId);

    //void deleteById(Integer salonId);
}
