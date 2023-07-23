package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.TipoMultimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoMultimediaRepository extends JpaRepository<TipoMultimedia, Integer> {

}
