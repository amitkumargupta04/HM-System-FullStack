package amit.hm.com.HM.System.service;

import amit.hm.com.HM.System.dto.auth.AuthLoginResponseDto;
import amit.hm.com.HM.System.dto.auth.AuthRequestDto;
import amit.hm.com.HM.System.dto.auth.AuthSignupResponseDto;
import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.entity.enums.Role;
import amit.hm.com.HM.System.repository.UserRepository;
import amit.hm.com.HM.System.util.JwtUtil;
import amit.hm.com.HM.System.util.PasswordUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    public ApiResponseDto<AuthSignupResponseDto> signup(AuthRequestDto requestDto) {
        if (userRepository.existsByEmail(requestDto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(requestDto.getEmail());
        user.setPassword(PasswordUtil.hashPassword(requestDto.getPassword()));
        user.setRoles(Collections.singleton(Role.PATIENT)); // default role
        user.setCreatedAt(LocalDateTime.now());
        user.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(user);

        AuthSignupResponseDto responseDto = new AuthSignupResponseDto();
        responseDto.setId(savedUser.getId());
        responseDto.setEmail(savedUser.getEmail());
        responseDto.setRole(savedUser.getRoles().iterator().next().name()); // single role
        responseDto.setCreatedAt(savedUser.getCreatedAt());
        responseDto.setUpdatedAt(savedUser.getUpdatedAt());

        return ApiResponseDto.<AuthSignupResponseDto>builder()
                .success(true)
                .message("Signup successful")
                .data(responseDto)
                .total(1) //
                .timestamp(LocalDateTime.now())
                .build();
    }

    public AuthLoginResponseDto login(AuthRequestDto dto){
        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new RuntimeException("Email incorrect"));

        if(!PasswordUtil.matchPassword(dto.getPassword(), user.getPassword())){
            throw new RuntimeException("Incorrect password");
        }
        String token = JwtUtil.generateToken(user.getEmail());

        AuthLoginResponseDto response = new AuthLoginResponseDto();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setRole(user.getRoles().iterator().next().name());
        response.setCreatedAt(user.getCreatedAt());
        response.setUpdatedAt(user.getUpdatedAt());
        response.setToken(token);

        return response;
    }
}
