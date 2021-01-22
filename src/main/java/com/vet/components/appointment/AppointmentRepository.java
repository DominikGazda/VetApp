package com.vet.components.appointment;

import com.vet.components.doctor.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("SELECT a from Appointment a WHERE a.doctor = :doctor AND a.date = :date AND a.isActive = :active")
    List<Appointment> findAllActiveByDoctorAndDate(@Param("doctor") Doctor doctor, @Param("date") LocalDate date,
            @Param("active") boolean active);
}
