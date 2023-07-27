/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.service;


import com.tapgroup.pwsalonreservas.model.Multimedia;
import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.repository.MultimediaRepository;
import com.tapgroup.pwsalonreservas.service.dao.MultimediaServiceDao;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 *
 * @author chris
 */
@Service
public class MultimediaServiceImpl implements MultimediaServiceDao {
    MultimediaRepository multimediaRepository;
    @Override
    public ResponseEntity<?> deleteImage(Integer idMultimedia) {
            Optional<Multimedia> optionalMultimedia = multimediaRepository.findById(idMultimedia);
            if (!optionalMultimedia.isPresent()) {
                return ResponseEntity.notFound().build();
            }
        multimediaRepository.deleteById(idMultimedia);
            return ResponseEntity.ok().build();
        }


//public class RolServiceImpl extends GenericServiceImpl<Rol, Integer> implements GenericService<Rol, Integer> {
//
//    @Autowired
//    RolRepository rolRepository;
//
//    @Override
//    public CrudRepository<Rol, Integer> getDao() {
//        return rolRepository;
//    }


}

