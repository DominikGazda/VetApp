package com.vet.components.doctor;

import com.vet.components.appointment.AppointmentDto;
import com.vet.components.appointment.AppointmentMapper;
import com.vet.components.appointment.AppointmentRepository;
import com.vet.components.doctor.exceptions.DoctorNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DoctorService {

    private DoctorRepository doctorRepository;
    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;

    @Autowired
    public DoctorService(DoctorRepository doctorRepository, AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper){
        this.doctorRepository = doctorRepository;
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
    }

    public List<DoctorDto> getDoctors(){
        return doctorRepository.findAll()
                .stream()
                .map(DoctorMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public DoctorDto saveDoctor(DoctorDto dto){
        Doctor doctorToSave = DoctorMapper.mapToEntity(dto);
        Doctor savedDoctor = doctorRepository.save(doctorToSave);
        return DoctorMapper.mapToDto(savedDoctor);
    }

    public DoctorDto getDoctorById(Long idDoctor){
        Optional<Doctor> optionalDoctor = doctorRepository.findById(idDoctor);
        Doctor doctor = optionalDoctor.orElseThrow(DoctorNotFoundException::new);
        System.out.println(doctor.getAppointments());
        return DoctorMapper.mapToDto(doctor);
    }

    public DoctorDto updateDoctor(Long id, DoctorDto dto){
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctorToUpdate = optionalDoctor.orElseThrow(DoctorNotFoundException::new);
        doctorToUpdate.setFirstName(dto.getFirstName());
        doctorToUpdate.setLastName(dto.getLastName());
        Doctor updatedDoctor = doctorRepository.save(doctorToUpdate);
        return DoctorMapper.mapToDto(updatedDoctor);
    }

    public DoctorDto deleteDoctor(Long id){
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctorToDelete = optionalDoctor.orElseThrow(DoctorNotFoundException::new);
        doctorRepository.delete(doctorToDelete);
        return DoctorMapper.mapToDto(doctorToDelete);
    }

    public List<AppointmentDto> getAppointmentsForDoctor(Long id, String localDate) {
        LocalDate date;
        try{
            date = LocalDate.parse(localDate);
        }catch(DateTimeParseException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Date must match the expression RRRR-MM-DD");
        }
        Optional<Doctor> optionalDoctor = doctorRepository.findById(id);
        Doctor doctor = optionalDoctor.orElseThrow(DoctorNotFoundException::new);
        return  appointmentRepository.findAllActiveByDoctorAndDate(doctor, date, true)
                .stream()
                .map(appointmentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public void checkErrors(BindingResult result){
        List<ObjectError> errors = result.getAllErrors();
        String message = errors.stream()
                .map(ObjectError::getDefaultMessage)
                .map(String::toString)
                .collect(Collectors.joining());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }
}
