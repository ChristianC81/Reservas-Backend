/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.controller;

import com.tapgroup.pwsalonreservas.model.Salon;
import com.tapgroup.pwsalonreservas.model.Usuario;
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
import org.springframework.web.multipart.MultipartFile;

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
   @PostMapping("/postImage/{idSalon}")
    public ResponseEntity<?> postImage(@RequestParam("imagen") MultipartFile imagen, @PathVariable Integer idSalon) {
        return salonServiceDao.postImage(imagen, idSalon);
    }

   @GetMapping("/getImages/{idSalon}")
   public List<byte[]> getImages(@PathVariable Integer idSalon) {
       return salonServiceDao.getImages(idSalon);
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
@GetMapping("/activos")
public ResponseEntity<List<Salon>> listaSalons() {
    ResponseEntity<List<Salon>> response = salonServiceDao.salonesActivos();
    return response;
}

    // Método listar salones inactivos
    @GetMapping("/inactivos")
    public ResponseEntity<List<Salon>> listaSalonesInactivos() {
        ResponseEntity<List<Salon>> response = salonServiceDao.salonesInactivos();
        return response;
    }

    //Metodo para actualizar estado

    @PutMapping("/actualizarest/{id}")
    public ResponseEntity<Salon> actualizarEstadoSalon(@PathVariable Integer id, @RequestBody Salon u) {
        ResponseEntity<Salon> response = salonServiceDao.cambiarEstado(id);

        if (response.getStatusCode().is2xxSuccessful()) {
            Salon salonActualizado = response.getBody();
            salonActualizado.setEstado(u.getEstado());

            try {
                Salon salonGuardado = salonServiceDao.save(salonActualizado);
                return new ResponseEntity<>(salonGuardado, HttpStatus.CREATED);
            } catch (Exception e) {
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>(response.getStatusCode());
        }
    }

}
