package amit.hm.com.HM.System.mapper;

import amit.hm.com.HM.System.dto.admin.CreateDocReqDto;
import amit.hm.com.HM.System.dto.admin.CreateDocResDto;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.entity.enums.Role;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class AdminDoctorMapper {

    public User toUser(CreateDocReqDto dto, PasswordEncoder passwordEncoder){
        User user =new User();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode("123456"));
        user.setRoles(Set.of(Role.DOCTOR));
        return user;
    }

    public CreateDocResDto toResponse(User user){
        CreateDocResDto res = new CreateDocResDto();
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        res.setRole("DOCTOR");
        res.setCreatedAt(user.getCreatedAt());
        res.setUpdatedAt(user.getUpdatedAt());
        return res;
    }
}
