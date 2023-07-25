/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.service;

import com.tapgroup.pwsalonreservas.model.Categoria;
import com.tapgroup.pwsalonreservas.model.Complemento;
import com.tapgroup.pwsalonreservas.model.Multimedia;
import com.tapgroup.pwsalonreservas.model.Salon;

import com.tapgroup.pwsalonreservas.model.TipoMultimedia;
import com.tapgroup.pwsalonreservas.model.Usuario;
import com.tapgroup.pwsalonreservas.model.dto.CategoriaDto;
import com.tapgroup.pwsalonreservas.model.dto.ComplementoDto;
import com.tapgroup.pwsalonreservas.model.dto.PublicacionDto;
import com.tapgroup.pwsalonreservas.model.dto.SalonDto;
import com.tapgroup.pwsalonreservas.model.dto.UserDto;
import com.tapgroup.pwsalonreservas.repository.CategoriaRepository;
import com.tapgroup.pwsalonreservas.repository.ComplementoRepository;
import com.tapgroup.pwsalonreservas.repository.MultimediaRepository;
import com.tapgroup.pwsalonreservas.repository.TipoMultimediaRepository;
import com.tapgroup.pwsalonreservas.repository.UsuarioRepository;
import com.tapgroup.pwsalonreservas.service.dao.SalonServiceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tapgroup.pwsalonreservas.repository.SalonRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author chris
 */
@Service
public class SalonServiceImpl implements SalonServiceDao {

    private final Integer idTipoMultimediaImage = 1;

    @Autowired
    SalonRepository salonRepository;

    @Autowired
    CategoriaRepository categoriaRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    private ComplementoRepository complementoRepository;

    @Autowired
    TipoMultimediaRepository tipoMultimediaRepository;

    @Autowired
    private MultimediaRepository multimediaRepository;

    @Override
    public ResponseEntity<?> getAllCategories() {
        return new ResponseEntity<>(categoriaRepository.findByEstado(true), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> postSalon(SalonDto salon, String emailUsuario) {
        // VALIDAR EXISTENCIA DE SALON
        Salon salonExistente = salonRepository.findByNombreLike(salon.getNombre());
        if(salon.getNombre().length() < 3 || salon.getNombre().length() > 30){
            return new ResponseEntity<>("El nombre del salon debe tener entre 3 y 30 caracteres", HttpStatus.BAD_REQUEST);
        }

        if (null != salonExistente) {
            return new ResponseEntity<>("Ya existe un salon registrado con el nombre " + salon.getNombre() + ".", HttpStatus.CONFLICT);
        }

        // VALIDAR EXISTENCIA DE CATEGORIA
        Categoria categoria = categoriaRepository.findById( salon.getCategoria().getIdCategoria()).orElse(null);
        if (null == categoria) {
            return new ResponseEntity<>("No existe la categoria", HttpStatus.NOT_FOUND);
        }

        // VALIDAR LA EXISTENCIA DEL USUARIO
        Usuario usuario = usuarioRepository.findByEmail(emailUsuario);
        if (null == usuario) {
            return new ResponseEntity<>("No existe el usuario", HttpStatus.NOT_FOUND);
        }

        Salon newSalon = new Salon();
        newSalon.setDisponibilidad(true); // disponibilidad
        newSalon.setNombre(salon.getNombre()); // nombre
        newSalon.setDireccion(salon.getDireccion()); // direccion
        newSalon.setCapacidad(salon.getCapacidad()); // capacidad
        newSalon.setDisponibilidad(true); // disponibilidad
        newSalon.setDescripcion(salon.getDescripcion()); // descripcion
        newSalon.setPrecioSalon(salon.getPrecioSalon()); // precio salon
        newSalon.setCalificacion(5.0);
        newSalon.setEstado(true); // estado
        newSalon.setUsuarioPublicador(usuario); // usuario publicador
        newSalon.setCategoria(categoria); // categoria
        newSalon.setGarantiaDanos(salon.getGarantiaDanos()); // garantia danos
        newSalon = salonRepository.save(newSalon);

        Salon finalNewSalon = newSalon;
        salon.getComplementos().forEach(complemento -> {
            Complemento com = new Complemento();
            com.setSalonByIdSalon(finalNewSalon);
            com.setNombre(complemento.getNombre());
            com.setDescripcion(complemento.getDescripcion());
            com.setCantidadBase(complemento.getCantidadBase());
            com.setCantidadRestante(complemento.getCantidadBase());
            com.setPrecioUnitario(complemento.getPrecioUnitario());
            com.setEstado(true);
            complementoRepository.save(com);
        });

        salon.setIdSalon(newSalon.getIdSalon());

        return new ResponseEntity<>(salon, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<?> postImage(byte[] imagen, Integer idSalon) {

        Salon salon = salonRepository.findById(idSalon).orElse(null);
        if (null == salon) {
            return new ResponseEntity<>("No existe el salon", HttpStatus.NOT_FOUND);
        }

        TipoMultimedia tipoMultimedia = tipoMultimediaRepository.findById(idTipoMultimediaImage).orElse(null);
        if (null == tipoMultimedia) {
            return new ResponseEntity<>("No existe el tipo de multimedia", HttpStatus.NOT_FOUND);
        }

        Multimedia multimedia = new Multimedia();
        multimedia.setSalon(salon);
        multimedia.setTipoMultimedia(tipoMultimedia);
        multimedia.setUrl("");
        multimedia.setImagen(null);

        multimediaRepository.save(multimedia);

        return new ResponseEntity<>("Imagen guardada", HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<?> listPosts() {

        List<Salon> salones = salonRepository.findByEstadoAndDisponibilidad(true, true);

        return getListSalonPublicaciones(salones);
    }

    @Override
    public ResponseEntity<?> listPostsByUser(String email) {

        Usuario usuario = usuarioRepository.findByEmail(email);
        if (null == usuario) {
            return new ResponseEntity<>("No existe el usuario", HttpStatus.NOT_FOUND);
        }

        List<Salon> salones = usuario.getSalonesByIdUsuario();

        return getListSalonPublicaciones(salones);

    }

    public ResponseEntity<?> deleteById(Integer salonId) {

        Salon salon = salonRepository.findById(salonId).orElse(null);
        if (salon == null) {
            return new ResponseEntity<>("No existe el salón con el ID proporcionado", HttpStatus.NOT_FOUND);
        }

        salonRepository.deleteById(salonId);
        return new ResponseEntity<>("Salón eliminado correctamente", HttpStatus.OK);
    }


    private ResponseEntity<?> getListSalonPublicaciones(List<Salon> salones) {
        List<PublicacionDto> salonesDto = new ArrayList<>();

        salones.forEach((salon) -> {
            SalonDto salonDto = new SalonDto();
            salonDto.setIdSalon(salon.getIdSalon());
            salonDto.setNombre(salon.getNombre());
            salonDto.setDireccion(salon.getDireccion());
            salonDto.setCapacidad(salon.getCapacidad());
            salonDto.setDescripcion(salon.getDescripcion());
            salonDto.setPrecioSalon(salon.getPrecioSalon());
            salonDto.setCalificacion(salon.getCalificacion());
            salonDto.setDisponibilidad(salon.getDisponibilidad());

            CategoriaDto categoriaDto = new CategoriaDto();
            categoriaDto.setIdCategoria(salon.getCategoria().getIdCategoria());
            categoriaDto.setCategoria(salon.getCategoria().getCategoria());
            categoriaDto.setEstado(salon.getCategoria().getEstado());

            salonDto.setCategoria(categoriaDto);

            List<ComplementoDto> complementosDto = new ArrayList<>();

            salon.getComplementosBySalon().forEach((complemento -> {
                ComplementoDto complementoDto = new ComplementoDto();
                complementoDto.setIdComplemento(complemento.getIdComplemento());
                complementoDto.setNombre(complemento.getNombre());
                complementoDto.setDescripcion(complemento.getDescripcion());
                complementoDto.setCantidadBase(complemento.getCantidadBase());
                complementoDto.setCantidadRestante(complemento.getCantidadRestante());
                complementoDto.setPrecioUnitario(complemento.getPrecioUnitario());
                complementoDto.setEstado(complemento.getEstado());

                complementosDto.add(complementoDto);
            }));

            salonDto.setComplementos(complementosDto);

            UserDto userDto = new UserDto();
            userDto.setIdUser(salon.getUsuarioPublicador().getIdUsuario());
            userDto.setEmail(salon.getUsuarioPublicador().getEmail());
            userDto.setUsername(salon.getUsuarioPublicador().getNombreUsuario());

            PublicacionDto publicacionDto = new PublicacionDto();
            publicacionDto.setSalonDto(salonDto);
            publicacionDto.setUserDto(userDto);
//            publicacionDto.setMultimedia(salon.getMultimediaBySalon());

            salonesDto.add(publicacionDto);
        });

        return new ResponseEntity<>(salonesDto, HttpStatus.OK);
    }
    @Override
    public Salon save(Salon Salon) {
        return salonRepository.save(Salon);
    }

    // Metodo listar Salons activos
    @Override
    public ResponseEntity<List<Salon>> salonesActivos() {
        List<Salon> SalonesActivos = salonRepository.findByEstado(true);
        return new ResponseEntity<>(SalonesActivos, HttpStatus.OK);
    }

    // Metodo listar Salons inactivos
    @Override
    public ResponseEntity<List<Salon>> salonesInactivos() {
        List<Salon> SalonesInactivos = salonRepository.findByEstado(false);
        return new ResponseEntity<>(SalonesInactivos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Salon> cambiarEstado(int id) {
        Optional<Salon> optionalSalon = salonRepository.findById(id);
        if (optionalSalon.isPresent()) {
            Salon Salon = optionalSalon.get();
            Salon.setEstado(!Salon.getEstado()); // Cambiar el estado del Salon
            salonRepository.save(Salon); // Guardar el Salon actualizado en la base de datos
            return new ResponseEntity<>(Salon, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}

