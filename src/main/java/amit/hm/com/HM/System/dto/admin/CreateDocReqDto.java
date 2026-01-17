package amit.hm.com.HM.System.dto.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateDocReqDto {
    private String email;
    //private String password;
    private String roles;
}
