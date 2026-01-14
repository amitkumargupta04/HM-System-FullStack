package amit.hm.com.HM.System.dto.patient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class PatientResponseDto {

    private Long id;
    private String fullName;
    private String email;
    private Integer age;
    private String gender;
    private String bloodGroup;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
