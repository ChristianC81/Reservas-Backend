/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tapgroup.pwsalonreservas.controller;

import com.tapgroup.pwsalonreservas.model.Multimedia;
import com.tapgroup.pwsalonreservas.model.Salon;
import com.tapgroup.pwsalonreservas.service.MultimediaServiceImpl;
import com.tapgroup.pwsalonreservas.service.RolServiceImpl;
import com.tapgroup.pwsalonreservas.service.dao.MultimediaServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author jonny
 */
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/multimedia")
public class MultimediaController {

    @Autowired
    MultimediaServiceDao multimediaServiceDao;

    //@DeleteMapping("/eliminar/{id}")
    //public ResponseEntity<?> eliminarUsuario(@PathVariable Integer id) {
        //return multimediaServiceDao.deleteImage(id);
    //}

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Multimedia> eliminarMultimedia(@PathVariable Integer id) {multimediaServiceDao.deleteBySalonId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/listarMultimedias")
    public ResponseEntity<?> listarPublicaciones() {
        return multimediaServiceDao.listMultimedias();
    }

    //    @Operation(summary = "Se obtiene la lista de Roles")
//    @GetMapping("/listar")
//    public ResponseEntity<List<Rol>> listaRoles() {
//        return new ResponseEntity<>(rolService.findByAll(), HttpStatus.OK);
//    }
//
//    @Operation(summary = "Debe enviar los campos del Rol")
//    @PostMapping("/crear")
//    public ResponseEntity<Rol> crearRol(@RequestBody Rol r) {
//        return new ResponseEntity<>(rolService.save(r), HttpStatus.CREATED);
//    }

//    @PutMapping("/actualizar/{id}")
//    public ResponseEntity<Rol> actualizarRol(@PathVariable Integer id, @RequestBody Rol r) {
//        Rol rol = rolService.findById(id);
//        if (rol != null) {
//            try {
//                rol.setRolNombre(r.getRolNombre());
//                rol.setRolDescripcion(r.getRolDescripcion());
//
//                return new ResponseEntity<>(rolService.save(rol), HttpStatus.CREATED);
//            } catch (Exception e) {
//                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
//            }
//
//        } else {
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//        }
//    }

//    @DeleteMapping("/eliminar/{id}")
//    public ResponseEntity<Rol> eliminarRol(@PathVariable Integer id) {
//        rolService.delete(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

}
