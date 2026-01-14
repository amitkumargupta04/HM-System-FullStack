package amit.hm.com.HM.System.controller;

import amit.hm.com.HM.System.dto.auth.AuthLoginResponseDto;
import amit.hm.com.HM.System.dto.auth.AuthRequestDto;
import amit.hm.com.HM.System.dto.auth.AuthSignupResponseDto;
import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<ApiResponseDto<AuthSignupResponseDto>> signup(@RequestBody AuthRequestDto dto){
        ApiResponseDto<AuthSignupResponseDto> response = authService.signup(dto);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/login")
    public ResponseEntity<ApiResponseDto<AuthLoginResponseDto>> login( @RequestBody AuthRequestDto dto){
        AuthLoginResponseDto authLoginResponseDto = authService.login(dto);
        ApiResponseDto<AuthLoginResponseDto> response = new ApiResponseDto<>(
                true,
                "Login Successfully",
                1,
                authLoginResponseDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
