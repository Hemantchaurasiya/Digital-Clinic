package com.digitalclinic.Digital.Clinic.dtos;

import lombok.Data;

@Data
public class JwtAuthResponse {
    private String token;
    private UserDto user;
}