package com.vet.components.appointment;

import com.vet.components.customer.Customer;
import com.vet.components.customer.CustomerRepository;
import com.vet.components.customer.exceptions.CustomerNotFoundException;
import com.vet.components.doctor.Doctor;
import com.vet.components.doctor.DoctorRepository;
import com.vet.components.doctor.exceptions.DoctorNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentMapper {

    private CustomerRepository customerRepository;
    private DoctorRepository doctorRepository;

    public AppointmentMapper(CustomerRepository customerRepository, DoctorRepository doctorRepository){
        this.customerRepository = customerRepository;
        this.doctorRepository = doctorRepository;
    }

    public AppointmentDto mapToDto(Appointment entity){
        AppointmentDto dto = new AppointmentDto();
        dto.setIdAppointment(entity.getIdAppointment());
        dto.setDate(entity.getDate());
        dto.setTime(entity.getTime());
        dto.setActive(entity.isActive());
        dto.setIdCustomer(entity.getCustomer().getIdCustomer());
        dto.setIdDoctor(entity.getDoctor().getIdDoctor());
        return dto;
    }

    public  Appointment mapToEntity(AppointmentDto dto){
        Optional<Customer> optionalCustomer = customerRepository.findById(dto.getIdCustomer());
        Customer customer = optionalCustomer.orElseThrow(CustomerNotFoundException::new);

        Optional<Doctor> optionalDoctor = doctorRepository.findById(dto.getIdDoctor());
        Doctor doctor = optionalDoctor.orElseThrow(DoctorNotFoundException::new);

        Appointment appointment = new Appointment();
        appointment.setIdAppointment(dto.getIdAppointment());
        appointment.setDate(dto.getDate());
        appointment.setTime(dto.getTime());
        appointment.setCustomer(customer);
        appointment.setDoctor(doctor);
        appointment.setActive(dto.isActive());
        return appointment;
    }
}
