package com.vet.components.appointment;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.vet.components.customer.CustomerDto;
import com.vet.components.doctor.DoctorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/appointment")
public class AppointmentController {

    private AppointmentService appointmentService;

    @Autowired
    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppointmentDto> getAppointments(){
        return appointmentService.getAppointments();
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDto> saveAppointment(@Valid @RequestBody AppointmentCustomerDto dto, BindingResult result){
        if(result.hasErrors())
            appointmentService.checkErrors(result);
        if(dto.getIdAppointment() != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Appointment cannot have id");
        AppointmentDto savedAppointment = appointmentService.saveAppointment(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedAppointment.getIdAppointment())
                .toUri();
        return ResponseEntity.created(location).body(savedAppointment);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppointmentDto getAppointmentById(@PathVariable Long id){
        return appointmentService.getAppointmentById(id);
    }

    @PutMapping(path = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AppointmentDto> updateAppointment(@PathVariable Long id,@Valid @RequestBody AppointmentDto dto, BindingResult result){
        if(result.hasErrors())
            appointmentService.checkErrors(result);
        if(!id.equals(dto.getIdAppointment()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Appointment must have id same as path variable");
        AppointmentDto updatedAppointment = appointmentService.updateAppointment(id, dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).body(updatedAppointment);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public AppointmentDto deleteAppointment(@PathVariable Long id){
        return appointmentService.deleteAppointment(id);
    }

    @GetMapping(path = "/{id}/doctor", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorDto getDoctorFromAppointment(@PathVariable Long id){
        return appointmentService.getDoctorFromAppointment(id);
    }

    @GetMapping(path = "/{id}/customer", produces = MediaType.APPLICATION_JSON_VALUE)
    public CustomerDto getCustomerFromAppointment(@PathVariable Long id){
        return appointmentService.getCustomerFromAppointment(id);
    }

    @PatchMapping(path = "/{appointmentId}/cancel" ,consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public AppointmentDto cancelAppointment(@PathVariable Long appointmentId, @RequestBody ObjectNode json){
        String id = json.get("id").asText();
        String pin = json.get("pin").asText();
        return appointmentService.cancelAppointment(appointmentId, id, pin);
    }
}
