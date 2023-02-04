package com.digitalclinic.Digital.Clinic.services.implemantation;

import com.digitalclinic.Digital.Clinic.dtos.MedicineDto;
import com.digitalclinic.Digital.Clinic.entities.Medicine;
import com.digitalclinic.Digital.Clinic.entities.Patient;
import com.digitalclinic.Digital.Clinic.exceptions.ResourceNotFoundException;
import com.digitalclinic.Digital.Clinic.repositories.MedicineRepository;
import com.digitalclinic.Digital.Clinic.repositories.PatientRepository;
import com.digitalclinic.Digital.Clinic.services.MedicineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MedicineServiceImpl implements MedicineService {

    @Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public MedicineDto createMedicine(Integer patientId,MedicineDto medicineDto) {
        Medicine medicine = modelMapper.map(medicineDto,Medicine.class);
        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(()->new ResourceNotFoundException("Patients","id",patientId));
        medicine.setPatient(patient);
        Medicine savedMedicine = medicineRepository.save(medicine);
        return modelMapper.map(savedMedicine,MedicineDto.class);
    }

    @Override
    public MedicineDto getMedicineById(Integer id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medicine","id",id));
        return modelMapper.map(medicine,MedicineDto.class);
    }

    @Override
    public List<MedicineDto> getAllMedicineOfPatient(Integer patientId) {
        List<Medicine> medicines = medicineRepository.findAll();
        List<MedicineDto> medicineDtoList = medicines.stream()
                .map((medicine)->modelMapper.map(medicine,MedicineDto.class))
                .collect(Collectors.toList());
        return medicineDtoList;
    }

    @Override
    public MedicineDto updateMedicine(MedicineDto medicineDto,Integer id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medicine","id",id));
        if (medicineDto.getName()!=null){
            medicine.setName(medicineDto.getName());
        }
        if (medicineDto.getDisease()!=null){
            medicine.setDisease(medicineDto.getDisease());
        }
        if (medicineDto.getDescription()!=null){
            medicine.setDescription(medicineDto.getDescription());
        }
        if (medicineDto.getDate()!=null){
            medicine.setDate(medicineDto.getDate());
        }
        Medicine savedMedicine = medicineRepository.save(medicine);
        return modelMapper.map(savedMedicine,MedicineDto.class);
    }

    @Override
    public void deleteMedicine(Integer id) {
        Medicine medicine = medicineRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Medicine","id",id));
        medicineRepository.delete(medicine);
    }
}
