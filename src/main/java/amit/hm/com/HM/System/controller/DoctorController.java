package amit.hm.com.HM.System.controller;

import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.dto.doctor.AppointmentStatusUpdateReqDto;
import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateReqDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateResDto;
import amit.hm.com.HM.System.dto.patient.AppointmentCreateResDto;
import amit.hm.com.HM.System.service.DoctorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    // Get Own Appointments
    @GetMapping("/appointment/{doctorId}")
    public ResponseEntity<ApiResponseDto<List<AppointmentCreateResDto>>>  getOwnAppointment(
            @PathVariable Long doctorId
    ){
        List<AppointmentCreateResDto> appointmentCreateResDtos = doctorService.getAppointmentsForDoctor(doctorId);
        ApiResponseDto<List<AppointmentCreateResDto>> response = new ApiResponseDto<>(
                true,
                "Doctors Appointments..",
                1,
                appointmentCreateResDtos,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    @PutMapping("/appointment/{doctorId}/{appointmentId}/status")
    public ResponseEntity<ApiResponseDto<AppointmentCreateResDto>> updateAppointmentStatus(
            @PathVariable Long doctorId,
            @PathVariable Long appointmentId,
            @RequestBody AppointmentStatusUpdateReqDto dto
    ){
        AppointmentCreateResDto appointmentCreateResDto = doctorService.updateAppointmentStatus(
                doctorId, appointmentId, dto.getStatus()
        );
        ApiResponseDto<AppointmentCreateResDto> response = new ApiResponseDto<>(
                true,
                "Status updated Successfully",
                1,
                appointmentCreateResDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
}
