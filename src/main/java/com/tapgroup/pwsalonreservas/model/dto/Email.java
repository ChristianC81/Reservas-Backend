/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.tapgroup.pwsalonreservas.model.dto;

import lombok.Data;

/**
 *
 * @author Usuario
 */
@Data
public class Email {

    private String to;
    private String from;
    private String subject;
    private String text;

}
