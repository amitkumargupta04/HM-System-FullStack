package amit.hm.com.HM.System.controller;

import amit.hm.com.HM.System.dto.admin.CreateDocReqDto;
import amit.hm.com.HM.System.dto.admin.CreateDocResDto;
import amit.hm.com.HM.System.dto.common.ApiResponseDto;
import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.patient.AppointmentCreateResDto;
import amit.hm.com.HM.System.dto.patient.PatientResponseDto;
import amit.hm.com.HM.System.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    // Create Doctors
    @PostMapping("/doctor/create")
    public ResponseEntity<ApiResponseDto<CreateDocResDto>> createDoctor(@RequestBody CreateDocReqDto dto){
        CreateDocResDto createDocResDto = adminService.createDoctor(dto);
        ApiResponseDto<CreateDocResDto> response = new ApiResponseDto<>(
                true,
                "Dcotor created successfully",
                1,
                createDocResDto,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    // Get All Doctors
    @GetMapping("/doctor/all")
    public ResponseEntity<ApiResponseDto<List<DoctorOwnProfileResDto>>> getAllDoctors(){
        List<DoctorOwnProfileResDto> doctors = adminService.getAllDoctor();
        ApiResponseDto<List<DoctorOwnProfileResDto>> response = new ApiResponseDto<>(
                true,
                "All Doctors fetched",
                doctors.size(),
                doctors,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    // Get All Patient
    @GetMapping("/patient/all")
    public ResponseEntity<ApiResponseDto<List<PatientResponseDto>>> getAllPatients(){
        List<PatientResponseDto> patients = adminService.getAllPatients();
        ApiResponseDto<List<PatientResponseDto>> response = new ApiResponseDto<>(
                true,
                "All Patient fetched",
                patients.size(),
                patients,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    // Delete Doctors
    @DeleteMapping("/doctor/delete/{id}")
    public ResponseEntity<ApiResponseDto<Void>> deleteDoctor(@PathVariable Long id){
        adminService.deleteDoctor(id);
        ApiResponseDto<Void> response = new ApiResponseDto<>(
                true,
                "Doctor Deleted Successfully",
                1,
                null,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }
    // get All Appointments
    @GetMapping("/appointment/all")
    public ResponseEntity<ApiResponseDto<List<AppointmentCreateResDto>>> getAllAppointments(){
        List<AppointmentCreateResDto> appointmentCreateResDtos = adminService.getAllAppointments();
        ApiResponseDto<List<AppointmentCreateResDto>> response = new ApiResponseDto<>(
                true,
                "All AppointMents Fetched..",
                appointmentCreateResDtos.size(),
                appointmentCreateResDtos,
                LocalDateTime.now()
        );
        return ResponseEntity.ok(response);
    }

}
