package com.digitalclinic.Digital.Clinic.services.implemantation;

import com.digitalclinic.Digital.Clinic.entities.Address;
import com.digitalclinic.Digital.Clinic.dtos.DoctorDto;
import com.digitalclinic.Digital.Clinic.entities.Doctor;
import com.digitalclinic.Digital.Clinic.exceptions.ResourceNotFoundException;
import com.digitalclinic.Digital.Clinic.repositories.AddressRepository;
import com.digitalclinic.Digital.Clinic.repositories.DoctorRepository;
import com.digitalclinic.Digital.Clinic.repositories.UserRepository;
import com.digitalclinic.Digital.Clinic.services.DoctorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public DoctorDto registerDoctor(DoctorDto doctorDto) {
        Doctor doctor = modelMapper.map(doctorDto,Doctor.class);
        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor,DoctorDto.class);
    }

    @Override
    public DoctorDto updateDoctor(DoctorDto doctorDto, Integer id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor","id",id));
        if (doctorDto.getFirstname()!=null){
            doctor.setFirstname(doctorDto.getFirstname());
        }
        if(doctorDto.getLastname()!=null){
            doctor.setLastname(doctorDto.getLastname());
        }
        if (doctorDto.getEmail()!=null){
            doctor.setEmail(doctorDto.getEmail());
        }
        if (doctorDto.getPhone()!=null){
            doctor.setPhone(doctorDto.getPhone());
        }
        if (doctorDto.getAge()!=0){
            doctor.setAge(doctorDto.getAge());
        }
        if (doctor.getAddress()==null){
            Address address = new Address();
            address.setPlace(doctorDto.getAddress().getPlace());
            address.setCity(doctorDto.getAddress().getCity());
            address.setState(doctorDto.getAddress().getState());
            address.setCountry(doctorDto.getAddress().getCountry());
            doctor.setAddress(address);
        } else if (doctorDto.getAddress()!=null) {
            Address address = addressRepository.findById(doctor.getAddress().getId())
                    .orElseThrow(()->new ResourceNotFoundException("Address","id",id));
            address.setPlace(doctorDto.getAddress().getPlace());
            address.setCity(doctorDto.getAddress().getCity());
            address.setState(doctorDto.getAddress().getState());
            address.setCountry(doctorDto.getAddress().getCountry());
            doctor.setAddress(address);
        }
        if (doctorDto.getImage()!=null){
            doctor.setImage(doctorDto.getImage());
        }
        if (doctorDto.getDepartment()!=null){
            doctor.setDepartment(doctorDto.getDepartment());
        }
        if (doctorDto.getSpecialist()!=null){
            doctor.setSpecialist(doctorDto.getSpecialist());
        }
        if (doctorDto.getEducation()!=null){
            doctor.setEducation(doctorDto.getEducation());
        }
        Doctor savedDoctor = doctorRepository.save(doctor);
        return modelMapper.map(savedDoctor,DoctorDto.class);
    }

    @Override
    public DoctorDto getDoctorById(Integer id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor","id",id));
        return modelMapper.map(doctor,DoctorDto.class);
    }

    @Override
    public DoctorDto getDoctorByName(String name) {
        return null;
    }

    @Override
    public List<DoctorDto> getAllDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        List<DoctorDto> doctorDtoList = doctors.stream()
                .map(doctor -> modelMapper.map(doctor,DoctorDto.class))
                .collect(Collectors.toList());
        return doctorDtoList;
    }

    @Override
    public void deleteDoctor(Integer id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("Doctor","id",id));
        doctorRepository.delete(doctor);
    }
}
