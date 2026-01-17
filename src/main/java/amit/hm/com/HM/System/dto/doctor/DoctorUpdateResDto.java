package amit.hm.com.HM.System.dto.doctor;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DoctorUpdateResDto {
    private Long id;
    private String fullName;
    private String specialization;
    private Integer experience;
    private Boolean available;
    private String imageUrl;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
