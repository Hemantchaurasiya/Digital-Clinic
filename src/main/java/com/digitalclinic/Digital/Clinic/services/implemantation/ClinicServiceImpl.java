package com.digitalclinic.Digital.Clinic.services.implemantation;

import com.digitalclinic.Digital.Clinic.dtos.ClinicDto;
import com.digitalclinic.Digital.Clinic.entities.Address;
import com.digitalclinic.Digital.Clinic.entities.Clinic;
import com.digitalclinic.Digital.Clinic.exceptions.ResourceNotFoundException;
import com.digitalclinic.Digital.Clinic.repositories.AddressRepository;
import com.digitalclinic.Digital.Clinic.repositories.ClinicRepository;
import com.digitalclinic.Digital.Clinic.services.ClinicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClinicServiceImpl implements ClinicService {

    @Autowired
    private ClinicRepository clinicRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public ClinicDto registerClinic(ClinicDto clinicDto) {
        Clinic clinic = modelMapper.map(clinicDto, Clinic.class);
        Clinic savedClinic = clinicRepository.save(clinic);
        return modelMapper.map(savedClinic,ClinicDto.class);
    }

    @Override
    public ClinicDto getClinicById(Integer id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Clinic","Id",id));
        return modelMapper.map(clinic,ClinicDto.class);
    }

    @Override
    public List<ClinicDto> getAllClinics() {
        List<Clinic> clinicList = clinicRepository.findAll();
        List<ClinicDto> clinicDtoList = clinicList.stream()
                .map(clinic -> modelMapper.map(clinic,ClinicDto.class)).collect(Collectors.toList());
        return clinicDtoList;
    }

    @Override
    public ClinicDto updateClinic(ClinicDto clinicDto,Integer id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Clinic","Id",id));
        if (clinicDto.getName()!=null){
            clinic.setName(clinicDto.getName());
        }
        if (clinicDto.getOpeningTime()!=null){
            clinic.setOpeningTime(clinicDto.getOpeningTime());
        }
        if (clinicDto.getClosingTime()!=null){
            clinic.setClosingTime(clinicDto.getClosingTime());
        }
        if (clinic.getAddress()==null){
            Address address = new Address();
            address.setPlace(clinicDto.getAddress().getPlace());
            address.setCity(clinicDto.getAddress().getCity());
            address.setState(clinicDto.getAddress().getState());
            address.setCountry(clinicDto.getAddress().getCountry());
            clinic.setAddress(address);
        }
        else if (clinicDto.getAddress()!=null){
            Address address = addressRepository.findById(clinic.getAddress().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Address","id",id));
            address.setPlace(clinicDto.getAddress().getPlace());
            address.setCity(clinicDto.getAddress().getCity());
            address.setState(clinicDto.getAddress().getState());
            address.setCountry(clinicDto.getAddress().getCountry());
            clinic.setAddress(address);
        }
        Clinic updatedClinic = clinicRepository.save(clinic);
        return modelMapper.map(updatedClinic,ClinicDto.class);
    }

    @Override
    public void deleteClinic(Integer id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Clinic","Id",id));
        clinicRepository.delete(clinic);
    }
}
