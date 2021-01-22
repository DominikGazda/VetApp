package com.vet.components.appointment;

import org.springframework.stereotype.Service;

@Service
public class AppointmentCustomerMapper {

    private AppointmentMapper appointmentMapper;

    public AppointmentCustomerMapper(AppointmentMapper appointmentMapper){
        this.appointmentMapper = appointmentMapper;
    }

    public Appointment mapToEntity(AppointmentCustomerDto dto){
      AppointmentDto appointmentDto = new AppointmentDto();
      appointmentDto.setIdAppointment(dto.getIdAppointment());
      appointmentDto.setDate(dto.getDate());
      appointmentDto.setTime(dto.getTime());
      appointmentDto.setIdDoctor(dto.getIdDoctor());
      appointmentDto.setIdCustomer(dto.getIdCustomer());
      return appointmentMapper.mapToEntity(appointmentDto);
    }
}
