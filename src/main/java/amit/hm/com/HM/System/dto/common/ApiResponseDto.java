package amit.hm.com.HM.System.dto.common;

import lombok.*;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ApiResponseDto<T> {
    private boolean success;
    private String message;
    private long total;
    private T data;
    private LocalDateTime timestamp;
}
