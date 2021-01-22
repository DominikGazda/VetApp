package com.vet.components.appointment;

import com.vet.components.appointment.exceptions.AppointmentNotFoundException;
import com.vet.components.customer.Customer;
import com.vet.components.customer.CustomerDto;
import com.vet.components.customer.CustomerMapper;
import com.vet.components.customer.CustomerRepository;
import com.vet.components.customer.exceptions.CustomerNotFoundException;
import com.vet.components.doctor.DoctorDto;
import com.vet.components.doctor.DoctorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AppointmentService {

    private AppointmentRepository appointmentRepository;
    private AppointmentMapper appointmentMapper;
    private AppointmentCustomerMapper appointmentCustomerMapper;
    private CustomerRepository customerRepository;

    @Autowired
    public AppointmentService(AppointmentRepository appointmentRepository, AppointmentMapper appointmentMapper, CustomerRepository customerRepository, AppointmentCustomerMapper appointmentCustomerMapper){
        this.appointmentRepository = appointmentRepository;
        this.appointmentMapper = appointmentMapper;
        this.customerRepository = customerRepository;
        this.appointmentCustomerMapper = appointmentCustomerMapper;
    }

    public List<AppointmentDto> getAppointments(){
        return appointmentRepository.findAll()
                .stream()
                .map(appointmentMapper::mapToDto)
                .collect(Collectors.toList());
    }

    public AppointmentDto saveAppointment(AppointmentCustomerDto dto){
        this.checkAppointmentIdAndPin(dto);
        this.checkAppointmentTimeForAppointmentCustomer(dto);
        Appointment appointmentToSave = appointmentCustomerMapper.mapToEntity(dto);
        appointmentToSave.setActive(true);
        Appointment savedAppointment = appointmentRepository.save(appointmentToSave);
        return appointmentMapper.mapToDto(savedAppointment);
    }

    public AppointmentDto getAppointmentById(Long id){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        Appointment appointment = optionalAppointment.orElseThrow(AppointmentNotFoundException::new);
        return appointmentMapper.mapToDto(appointment);
    }

    public AppointmentDto updateAppointment(Long id, AppointmentDto dto){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        if(optionalAppointment.isEmpty())
            throw new AppointmentNotFoundException();
        this.checkAppointmentTimeForAppointment(dto);
        Appointment appointment = appointmentMapper.mapToEntity(dto);
        Appointment updatedAppointment = appointmentRepository.save(appointment);
        return appointmentMapper.mapToDto(updatedAppointment);
    }

    public AppointmentDto deleteAppointment(Long id){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        Appointment appointmentToDelete = optionalAppointment.orElseThrow(AppointmentNotFoundException::new);
        appointmentRepository.delete(appointmentToDelete);
        return appointmentMapper.mapToDto(appointmentToDelete);
    }

    public DoctorDto getDoctorFromAppointment(Long id){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        Appointment appointment = optionalAppointment.orElseThrow(AppointmentNotFoundException::new);
        return DoctorMapper.mapToDto(appointment.getDoctor());
    }

    public CustomerDto getCustomerFromAppointment(Long id){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(id);
        Appointment appointment = optionalAppointment.orElseThrow(AppointmentNotFoundException::new);
        return CustomerMapper.mapToDto(appointment.getCustomer());
    }

    public AppointmentDto cancelAppointment(Long assignmentId, String id, String pin){
        Optional<Appointment> optionalAppointment = appointmentRepository.findById(assignmentId);
        Appointment appointment = optionalAppointment.orElseThrow(AppointmentNotFoundException::new);
        Optional<Customer> optionalCustomer =  customerRepository.findById(appointment.getCustomer().getIdCustomer());
        Customer customer = optionalCustomer.orElseThrow(CustomerNotFoundException::new);
        if(!customer.getID().equals(id))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entered an incorrect ID");
        if(!customer.getPIN().equals(pin))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entered an incorrect PIN");
        appointment.setActive(false);
        appointmentRepository.save(appointment);
        return appointmentMapper.mapToDto(appointment);
    }

    public void checkErrors(BindingResult result){
        List<ObjectError> errors = result.getAllErrors();
        String message = errors.stream()
                .map(ObjectError::getDefaultMessage)
                .map(err -> err+" ")
                .collect(Collectors.joining());
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST,message);
    }

    private void checkAppointmentIdAndPin(AppointmentCustomerDto dto){
        Optional<Customer> optionalCustomer =  customerRepository.findById(dto.getIdCustomer());
        Customer customer = optionalCustomer.orElseThrow(CustomerNotFoundException::new);
        if(!customer.getID().equals(dto.getID()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entered an incorrect ID");
        if(!customer.getPIN().equals(dto.getPIN()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Entered an incorrect PIN");
    }

    private void checkAppointmentTimeForAppointmentCustomer(AppointmentCustomerDto dto){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(dto.getDate().equals(localDateTime.toLocalDate()))
            if(dto.getTime().isBefore(localDateTime.toLocalTime()))
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Past time provided");
    }

    private void checkAppointmentTimeForAppointment(AppointmentDto dto){
        LocalDateTime localDateTime = LocalDateTime.now();
        if(dto.getDate().equals(localDateTime.toLocalDate()))
            if(dto.getTime().isBefore(localDateTime.toLocalTime()))
                throw new ResponseStatusException(HttpStatus.CONFLICT,"Past time provided");
    }
}
