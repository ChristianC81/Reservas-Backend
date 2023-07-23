package com.tapgroup.pwsalonreservas.model.dto;

import com.tapgroup.pwsalonreservas.model.Multimedia;
import com.tapgroup.pwsalonreservas.model.Salon;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PublicacionDto {
    private SalonDto salonDto;
    private UserDto userDto;
    private List<Multimedia> multimedia;
}
