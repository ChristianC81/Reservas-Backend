/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.controller;

import com.tapgroup.pwsalonreservas.model.Salon;
import com.tapgroup.pwsalonreservas.model.dto.SalonDto;
import com.tapgroup.pwsalonreservas.service.SalonServiceImpl;
import com.tapgroup.pwsalonreservas.service.dao.SalonServiceDao;
import io.swagger.v3.oas.annotations.Operation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("api/salon")
public class SalonController {

    @Autowired
    SalonServiceDao salonServiceDao;

    @Operation(summary = "Debe enviar los campos de Salon")
    @PostMapping("/crear")
    public ResponseEntity<?> crearSalon(@RequestBody SalonDto salon, @RequestParam String emailPublicador) {
        return salonServiceDao.postSalon(salon, emailPublicador);
    }


    @PostMapping("/postImage")
    public ResponseEntity<?> postImage(@RequestBody byte[] imagen, @RequestParam Integer idSalon) {
        return salonServiceDao.postImage(imagen, idSalon);
    }

    @GetMapping("/listarPublicaciones")
    public ResponseEntity<?> listarPublicaciones() {
        return salonServiceDao.listPosts();
    }

    @GetMapping("/listPublicacionesByUser/{email}")
    public ResponseEntity<?> listByUser(@PathVariable String email) {
        return salonServiceDao.listPostsByUser(email);
    }

    @GetMapping("/listCategorias")
    public ResponseEntity<?> listCategorias() {
        return salonServiceDao.getAllCategories();
    }

//    @DeleteMapping("/eliminar/{id}")
//    public ResponseEntity<Salon> eliminarSalon(@PathVariable Integer id) {
//        salonService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    //Metodo listar salones activos
//    @GetMapping("/activos")
//    public ResponseEntity<List<Salon>> listaSalonesActivos() {
//        List<Salon> salonesActivos = salonService.salonesActivos();
//        return new ResponseEntity<>(salonesActivos, HttpStatus.OK);
//    }

    //Metodo listar salones inactivos
//    @GetMapping("/inactivos")
//    public ResponseEntity<List<Salon>> listaSalonesInactivos() {
//        List<Salon> salonesInactivos = salonService.salonesInactivos();
//        return new ResponseEntity<>(salonesInactivos, HttpStatus.OK);
//    }

    //Metodo para actualizar estado
//    @PutMapping("/actualizarest/{id}")
//    public ResponseEntity<Salon> actualizarEstadoSalon(@PathVariable Integer id, @RequestBody Salon s) {
//        Salon sal = salonService.findById(id);
//        if (sal != null) {
//            try {
//                sal.setSalonEstado(s.getSalonEstado());
//                return new ResponseEntity<>(salonService.save(sal), HttpStatus.CREATED);
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }
}
