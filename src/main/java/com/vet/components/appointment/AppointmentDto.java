package com.vet.components.appointment;

import com.vet.constraints.LocalDateFormatter;
import com.vet.constraints.LocalTimeFormatter;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

public class AppointmentDto {

    private Long idAppointment;

    @LocalDateFormatter
    private LocalDate date;

    @LocalTimeFormatter
    private LocalTime time;

    private boolean isActive;

    @NotNull(message = "{com.vet.components.AppointmentDto.idCustomer.NotNull}")
    private Long idCustomer;

    @NotNull(message = "{com.vet.components.AppointmentDto.idDoctor.NotNull}")
    private Long idDoctor;

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

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AppointmentDto that = (AppointmentDto) o;
        return isActive == that.isActive &&
                Objects.equals(idAppointment, that.idAppointment) &&
                Objects.equals(date, that.date) &&
                Objects.equals(time, that.time) &&
                Objects.equals(idCustomer, that.idCustomer) &&
                Objects.equals(idDoctor, that.idDoctor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAppointment, date, time, isActive, idCustomer, idDoctor);
    }
}
