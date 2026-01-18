package amit.hm.com.HM.System.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentCreateReqDto {
    private Long doctorId;                 // kis doctor ke saath
    private LocalDateTime appointmentTime; // kab
    private String reason;                 // optional: problem / reason
}
