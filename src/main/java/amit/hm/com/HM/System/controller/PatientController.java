package amit.hm.com.HM.System.controller;

import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.dto.patient.PatientRequestDto;
import amit.hm.com.HM.System.dto.patient.PatientResponseDto;
import amit.hm.com.HM.System.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RequestMapping("/patient")
@RequiredArgsConstructor
@RestController
public class PatientController {

    private final PatientService patientService;

    @PostMapping("/profile/update")
    public ResponseEntity<ApiResponseDto<PatientResponseDto>> updateProfile(@RequestBody PatientRequestDto dto){
        PatientResponseDto patientResponseDto = patientService.updateProfile(dto);
        ApiResponseDto<PatientResponseDto> response = new ApiResponseDto<>(
                true,
                "Updated successfully",
                1,
                patientResponseDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<PatientResponseDto>> getOwnProfile(){
        PatientResponseDto patientResponseDto = patientService.getOwnProfile();
        ApiResponseDto<PatientResponseDto> response = new ApiResponseDto<>(
                true,
                "Fetched Successfully",
                1,
                patientResponseDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
