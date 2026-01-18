package amit.hm.com.HM.System.controller;

import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.dto.patient.*;
import amit.hm.com.HM.System.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

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
    // Book Appointments
    @PostMapping("/appointment/book/{id}")
    public ResponseEntity<ApiResponseDto<AppointmentCreateResDto>> bookAppointment(@PathVariable Long id, @RequestBody AppointmentCreateReqDto dto){
        AppointmentCreateResDto appointmentCreateResDto = patientService.bookAppointment(id, dto);
        ApiResponseDto<AppointmentCreateResDto> response = new ApiResponseDto<>(
                true,
                "Appointment Booked Successfully",
                1,
                appointmentCreateResDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    // Update for reason
    @PutMapping("/appointment/update/{patientId}/{appointmentId}")
    public ResponseEntity<ApiResponseDto<AppointmentCreateResDto>> updateAppointmentReason(
            @PathVariable Long patientId,
            @PathVariable Long appointmentId,
            @RequestBody AppointmentReasonUpdateReqDto dto
    ) {

        AppointmentCreateResDto res =
                patientService.updateAppointmentReason(
                        patientId,
                        appointmentId,
                        dto.getReason()
                );

        return ResponseEntity.ok(
                new ApiResponseDto<>(
                        true,
                        "Appointment Reason Updated Successfully",
                        1,
                        res,
                        LocalDateTime.now()
                )
        );
    }

    // Delete AppointMents
    @DeleteMapping("/appointment/cancel/{appointmentId}/{patientId}")
    public ResponseEntity<ApiResponseDto<AppointmentCreateResDto>> cancelAppointment(
            @PathVariable Long patientId,
            @PathVariable Long appointmentId) {

        AppointmentCreateResDto cancelledAppointment = patientService.cancelAppointment(patientId, appointmentId);

        ApiResponseDto<AppointmentCreateResDto> response = new ApiResponseDto<>(
                true,
                "Appointment Cancelled Successfully",
                1,
                cancelledAppointment,
                LocalDateTime.now()
        );

        return ResponseEntity.ok(response);
    }

    @GetMapping("/appointment/{patientId}")
    public ResponseEntity<ApiResponseDto<List<AppointmentCreateResDto>>> getOwnAppointment(
            @PathVariable Long patientId
    ){
        List<AppointmentCreateResDto> appointmentCreateResDto = patientService.getAllAppointmentsForPatient(patientId);
        ApiResponseDto<List<AppointmentCreateResDto>> response = new ApiResponseDto<>(
                true,
                "Patients Appointments...",
                1,
                appointmentCreateResDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

}
