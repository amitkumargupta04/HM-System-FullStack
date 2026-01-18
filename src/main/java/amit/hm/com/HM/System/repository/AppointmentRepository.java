package amit.hm.com.HM.System.repository;

import amit.hm.com.HM.System.entity.Appointment;
import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    List<Appointment> findByDoctorAndAppointmentTime(Doctor doctor, LocalDateTime appointmentTime);
    List<Appointment> findByDoctor(Doctor doctor);
    List<Appointment> findByPatient(Patient patient);
}
