package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Multimedia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {

    Multimedia findByIdMultimedia(int i);
}
