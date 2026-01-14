package amit.hm.com.HM.System.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientRequestDto {

    private String fullName;
    private Integer age;
    private String gender;
    private String bloodGroup;

}
