package com.digitalclinic.Digital.Clinic.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class MedicineDto {
    private Integer id;
    private String name;
    private String disease;
    private String description;
    private Date date;
    private PatientDto patient;
}
