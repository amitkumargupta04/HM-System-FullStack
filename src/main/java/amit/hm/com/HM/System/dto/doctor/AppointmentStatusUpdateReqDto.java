package amit.hm.com.HM.System.dto.doctor;

import amit.hm.com.HM.System.entity.enums.AppointmentStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentStatusUpdateReqDto {
    private AppointmentStatus status;
}
