package amit.hm.com.HM.System.controller;

import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateReqDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateResDto;
import amit.hm.com.HM.System.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/doctor")
@RequiredArgsConstructor
public class DoctorController {

    private final DoctorService doctorService;

    // update own profile
    @PostMapping("/update")
    public ResponseEntity<ApiResponseDto<DoctorUpdateResDto>> doctorUpdateProfile(@RequestBody DoctorUpdateReqDto dto){
        DoctorUpdateResDto doctorUpdateResDto = doctorService.doctorUpdateProfile(dto);
        ApiResponseDto<DoctorUpdateResDto> response = new ApiResponseDto<>(
                true,
                "Profile Updated Successfully",
                1,
                doctorUpdateResDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    // getOwnProfile
    @GetMapping("/me")
    public ResponseEntity<ApiResponseDto<DoctorOwnProfileResDto>> getOwnProfile(){
        DoctorOwnProfileResDto resDto = doctorService.getOwnProfile();
        ApiResponseDto<DoctorOwnProfileResDto> response = new ApiResponseDto<>(
                true,
                "My Profile Fetched",
                1,
                resDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

}
