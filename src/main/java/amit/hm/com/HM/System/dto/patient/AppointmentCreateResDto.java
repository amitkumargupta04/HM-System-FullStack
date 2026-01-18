package amit.hm.com.HM.System.dto.patient;

import amit.hm.com.HM.System.entity.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateResDto {
    private Long appointmentId;           // appointment ka ID
    private Long doctorId;                // doctor ka ID
    private String doctorName;            // optional: doctor ka naam
    private Long patientId;               // patient ka ID
    private String patientName;           // optional: patient naam
    private LocalDateTime appointmentTime;// kab
    private AppointmentStatus status;     // BOOKED / PENDING / etc
    private String reason;                // patient ka note/problem
}
