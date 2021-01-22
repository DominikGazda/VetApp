package com.vet.components.doctor;

import com.vet.components.appointment.AppointmentDto;
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
@RequestMapping("/api/doctor")
public class DoctorController {

    private DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService){
        this.doctorService = doctorService;
    }

    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DoctorDto> getDoctors(){
        return doctorService.getDoctors();
    }

    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDto> saveDoctor(@Valid @RequestBody DoctorDto dto, BindingResult result){
        if(result.hasErrors())
            doctorService.checkErrors(result);
        if(dto.getIdDoctor() != null)
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Doctor cannot have id");
        DoctorDto savedDoctor = doctorService.saveDoctor(dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedDoctor.getIdDoctor())
                .toUri();
        return ResponseEntity.created(location).body(savedDoctor);
    }

    @GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorDto getDoctorById(@PathVariable Long id){
        return doctorService.getDoctorById(id);
    }

    @PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long id,@Valid @RequestBody DoctorDto dto, BindingResult result){
        if(result.hasErrors())
            doctorService.checkErrors(result);
        if(!id.equals(dto.getIdDoctor()))
            throw new ResponseStatusException(HttpStatus.CONFLICT,"Doctor must have id same as path variable");
        DoctorDto updatedDoctor = doctorService.updateDoctor(id, dto);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .build()
                .toUri();
        return ResponseEntity.created(location).body(updatedDoctor);
    }

    @DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public DoctorDto deleteDoctor(@PathVariable Long id){
        return doctorService.deleteDoctor(id);
    }

    @GetMapping(path = "/{id}/appointments", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<AppointmentDto> getAppointmentsForDoctor(@PathVariable Long id, @RequestParam(required = true) String date){
            return doctorService.getAppointmentsForDoctor(id, date);
    }
}
