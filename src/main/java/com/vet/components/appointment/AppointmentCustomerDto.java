package com.vet.components.appointment;

import com.vet.constraints.LocalDateFormatter;
import com.vet.constraints.LocalTimeFormatter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentCustomerDto {

    private Long idAppointment;

    @LocalDateFormatter
    private LocalDate date;

    @LocalTimeFormatter
    private LocalTime time;

    @NotNull(message = "{com.vet.components.AppointmentDto.idCustomer.NotNull}")
    private Long idCustomer;

    @NotNull(message = "{com.vet.components.AppointmentDto.idDoctor.NotNull}")
    private Long idDoctor;

    @Pattern(regexp = "^[0-9]{4}?$")
    private String ID;

    @Pattern(regexp = "^[0-9]{4}?$")
    private String PIN;

    public Long getIdAppointment() {
        return idAppointment;
    }

    public void setIdAppointment(Long idAppointment) {
        this.idAppointment = idAppointment;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public Long getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(Long idCustomer) {
        this.idCustomer = idCustomer;
    }

    public Long getIdDoctor() {
        return idDoctor;
    }

    public void setIdDoctor(Long idDoctor) {
        this.idDoctor = idDoctor;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getPIN() {
        return PIN;
    }

    public void setPIN(String PIN) {
        this.PIN = PIN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentCustomerDto that = (AppointmentCustomerDto) o;
        return Objects.equals(idAppointment, that.idAppointment) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(idCustomer, that.idCustomer) &&
                Objects.equals(idDoctor, that.idDoctor) &&
                Objects.equals(ID, that.ID) &&
                Objects.equals(PIN, that.PIN);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAppointment, date, time, idCustomer, idDoctor, ID, PIN);
    }
}
