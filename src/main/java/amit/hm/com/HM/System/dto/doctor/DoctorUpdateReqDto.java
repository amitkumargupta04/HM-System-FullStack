package amit.hm.com.HM.System.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateReqDto {
    private String fullName;
    private String specialization;
    private Integer experience;
    private Boolean available;
    private String imageUrl;
}
