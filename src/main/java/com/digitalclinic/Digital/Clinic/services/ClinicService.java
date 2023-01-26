package com.digitalclinic.Digital.Clinic.services;

import com.digitalclinic.Digital.Clinic.dtos.ClinicDto;

import java.util.List;

public interface ClinicService {
    ClinicDto registerClinic(ClinicDto clinicDto);
    ClinicDto getClinicById(Integer id);
    List<ClinicDto> getAllClinics();
    ClinicDto updateClinic(ClinicDto clinicDto,Integer id);
    void deleteClinic(Integer id);
}
