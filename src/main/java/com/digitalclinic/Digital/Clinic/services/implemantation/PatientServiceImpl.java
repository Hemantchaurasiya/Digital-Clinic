package com.digitalclinic.Digital.Clinic.services.implemantation;

import com.digitalclinic.Digital.Clinic.entities.Address;
import com.digitalclinic.Digital.Clinic.dtos.PatientDto;
import com.digitalclinic.Digital.Clinic.entities.Patient;
import com.digitalclinic.Digital.Clinic.exceptions.ResourceNotFoundException;
import com.digitalclinic.Digital.Clinic.repositories.AddressRepository;
import com.digitalclinic.Digital.Clinic.repositories.PatientRepository;
import com.digitalclinic.Digital.Clinic.services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto registerPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto,Patient.class);
        Patient savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient,PatientDto.class);
    }

    @Override
    public PatientDto getPatientById(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patients","id",id));
        return modelMapper.map(patient,PatientDto.class);
    }
    @Override
    public List<PatientDto> getPatientByName(String name) {
        List<Patient> patients = patientRepository.findByFirstname(name);
        List<PatientDto> patientDtoList = patients.stream()
                .map(patient -> modelMapper.map(patient,PatientDto.class))
                .collect(Collectors.toList());
        return patientDtoList;
    }
    @Override
    public PatientDto getPatientByPhone(String phone) {
        Patient patient = patientRepository.findByPhone(phone);
        return modelMapper.map(patient,PatientDto.class);
    }
    @Override
    public PatientDto getPatientByEmail(String email) {
        Patient patient = patientRepository.findByEmail(email);
        return modelMapper.map(patient,PatientDto.class);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        List<PatientDto> patientDtoList = patients.stream()
                .map(patient -> modelMapper.map(patient,PatientDto.class))
                .collect(Collectors.toList());
        return patientDtoList;
    }

    @Override
    public PatientDto updatePatient(PatientDto patientDto, Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor","id",id));
        if (patientDto.getFirstname()!=null){
            patient.setFirstname(patientDto.getFirstname());
        }
        if(patientDto.getLastname()!=null){
            patient.setLastname(patientDto.getLastname());
        }
        if (patientDto.getEmail()!=null){
            patient.setEmail(patientDto.getEmail());
        }
        if (patientDto.getPhone()!=null){
            patient.setPhone(patientDto.getPhone());
        }
        if (patientDto.getAge()!=0){
            patient.setAge(patientDto.getAge());
        }
        if (patient.getAddress()==null){
            Address address = new Address();
            address.setPlace(patientDto.getAddress().getPlace());
            address.setCity(patientDto.getAddress().getCity());
            address.setState(patientDto.getAddress().getState());
            address.setCountry(patientDto.getAddress().getCountry());
            patient.setAddress(address);
        }else if (patientDto.getAddress()!=null){
            Address address = addressRepository.findById(patient.getAddress().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Address","id",id));
            address.setPlace(patientDto.getAddress().getPlace());
            address.setCity(patientDto.getAddress().getCity());
            address.setState(patientDto.getAddress().getState());
            address.setCountry(patientDto.getAddress().getCountry());
            patient.setAddress(address);
        }
        if (patientDto.getImage()!=null){
            patient.setImage(patientDto.getImage());
        }
        if (patientDto.getWeight()!=0.0){
            patient.setWeight(patientDto.getWeight());
        }
        if (patientDto.getHeight()!=0){
            patient.setHeight(patientDto.getHeight());
        }
        if (patientDto.getBloodGroup()!=null){
            patient.setBloodGroup(patientDto.getBloodGroup());
        }
        Patient savedPatient = patientRepository.save(patient);
        return modelMapper.map(savedPatient, PatientDto.class);
    }

    @Override
    public void deletePatient(Integer id) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Patients","id",id));
        patientRepository.delete(patient);
    }
}
