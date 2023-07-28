package com.tapgroup.pwsalonreservas.service.dao;

import com.tapgroup.pwsalonreservas.model.dto.PedidoDto;
import org.springframework.http.ResponseEntity;


public interface MultimediaServiceDao {

    ResponseEntity<?> deleteImage(Integer idMultimedia);

    ResponseEntity<?> deleteBySalonId(Integer idSalon);


    ResponseEntity<?> listMultimedias();

}
