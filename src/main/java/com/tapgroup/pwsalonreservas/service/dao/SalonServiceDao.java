package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.Salon;
import com.tapgroup.pwsalonreservas.model.dto.SalonDto;
import org.springframework.http.ResponseEntity;

public interface SalonServiceDao {

    ResponseEntity<?> getAllCategories();

    ResponseEntity<?> postSalon(SalonDto salon, String emailPublicador);

    ResponseEntity<?> postImage(byte[] imagen, Integer idSalon);

    ResponseEntity<?> listPosts();

    ResponseEntity<?> listPostsByUser(String email);

}
