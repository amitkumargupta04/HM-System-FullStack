package amit.hm.com.HM.System.entity;

import amit.hm.com.HM.System.entity.enums.AppointmentStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // kis patient ne book ki
    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private Patient patient;
    // kis doctor ke sath
    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;

    // appointment date + time
    private LocalDateTime appointmentTime;

    // appointment status
    @Enumerated(EnumType.STRING)
    private AppointmentStatus status;

    // optional: patient ka note / problem
    private String reason;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}

