package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
}
