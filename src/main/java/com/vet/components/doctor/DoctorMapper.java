package com.vet.components.doctor;

public class DoctorMapper {

    public static DoctorDto mapToDto(Doctor entity){
        DoctorDto doctorDto = new DoctorDto();
        doctorDto.setIdDoctor(entity.getIdDoctor());
        doctorDto.setFirstName(entity.getFirstName());
        doctorDto.setLastName(entity.getLastName());
        return doctorDto;
    }

    public static Doctor mapToEntity(DoctorDto dto){
        Doctor doctor = new Doctor();
        doctor.setIdDoctor(dto.getIdDoctor());
        doctor.setFirstName(dto.getFirstName());
        doctor.setLastName(dto.getLastName());
        return doctor;
    }
}
