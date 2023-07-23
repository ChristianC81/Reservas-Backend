package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {

    List<Categoria> findByEstado(boolean estado);
}
