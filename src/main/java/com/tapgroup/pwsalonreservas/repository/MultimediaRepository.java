package com.tapgroup.pwsalonreservas.repository;

import com.tapgroup.pwsalonreservas.model.Multimedia;
import com.tapgroup.pwsalonreservas.model.Salon;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MultimediaRepository extends JpaRepository<Multimedia, Integer> {

    Multimedia findByIdMultimedia(int i);

    //@Modifying
    //@Query(value = "DELETE FROM Multimedia  WHERE id_salon = :id_salon", nativeQuery = true)
    //Multimedia deleteByidSalon(Integer id_salon);

    @Transactional
    @Modifying
    @Query("DELETE FROM Multimedia m WHERE m.salon.id = :idSalon")
    void deleteBySalonId(Integer idSalon);



}
