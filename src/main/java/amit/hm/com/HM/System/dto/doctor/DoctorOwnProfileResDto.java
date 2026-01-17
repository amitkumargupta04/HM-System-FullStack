package amit.hm.com.HM.System.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorOwnProfileResDto {
    private Long id;
    private String email;
    private String fullName;
    private String roles;
    private String specialization;
    private Integer experience;
    private Boolean available;
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
