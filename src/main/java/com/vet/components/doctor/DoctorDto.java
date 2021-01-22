package com.vet.components.doctor;

import javax.validation.constraints.NotBlank;
import java.util.Objects;

public class DoctorDto {

    private Long idDoctor;

    @NotBlank(message = "{com.vet.components.doctor.DoctorDto.firstName.NotBlank}")
    private String firstName;

    @NotBlank(message = "{com.vet.components.doctor.DoctorDto.lastName.NotBlank}")
    private String lastName;

    public Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DoctorDto doctorDto = (DoctorDto) o;
        return Objects.equals(idDoctor, doctorDto.idDoctor) &&
                Objects.equals(firstName, doctorDto.firstName) &&
                Objects.equals(lastName, doctorDto.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDoctor, firstName, lastName);
    }
}
