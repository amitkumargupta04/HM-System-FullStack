package amit.hm.com.HM.System.service;

import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateReqDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateResDto;
import amit.hm.com.HM.System.dto.patient.AppointmentCreateResDto;
import amit.hm.com.HM.System.entity.Appointment;
import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.entity.enums.AppointmentStatus;
import amit.hm.com.HM.System.mapper.AppointmentMapper;
import amit.hm.com.HM.System.mapper.DoctorMapper;
import amit.hm.com.HM.System.repository.AppointmentRepository;
import amit.hm.com.HM.System.repository.DoctorRepository;
import amit.hm.com.HM.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;
    private final AppointmentRepository appointmentRepository;
    // doctor update profile
    public DoctorUpdateResDto doctorUpdateProfile(DoctorUpdateReqDto dto){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
//        Doctor doctor = doctorRepository.findByUser(user)
//                .orElseGet(() -> {
//                    Doctor d = new Doctor();
//                    d.setUser(user);
//                    return d;
//                });
//        Doctor doctor = doctorRepository.findByUser(user)
//                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));
        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));

        // DTO → entity
        doctorMapper.updateDoctorFromDto(dto, doctor);

        Doctor savedDoctor = doctorRepository.save(doctor);

        return doctorMapper.toUpdateResponse(doctor);
    }

    // getOwnProfile
    public DoctorOwnProfileResDto getOwnProfile() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Doctor doctor = doctorRepository.findByUserId(user.getId())
                .orElseThrow(() -> new RuntimeException("Doctor profile not found"));

        return doctorMapper.toOwnProfileResponse(doctor);
    }

    // get all appointments for a specific doctor
    public List<AppointmentCreateResDto> getAppointmentsForDoctor(Long doctorId) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        return appointmentRepository.findByDoctor(doctor)
                .stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }
    // Status update of Appointment
    public AppointmentCreateResDto updateAppointmentStatus(
            Long doctorId,
            Long appointmentId,
            AppointmentStatus newStatus
    ) {

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        // ownership check
        if (!appointment.getDoctor().getId().equals(doctorId)) {
            throw new RuntimeException("You are not authorized to update this appointment");
        }

        // business rule (optional)
        if (appointment.getStatus() == AppointmentStatus.CANCELLED) {
            throw new RuntimeException("Cancelled appointment cannot be updated");
        }

        appointment.setStatus(newStatus);
        Appointment saved = appointmentRepository.save(appointment);

        return AppointmentMapper.toResponse(saved);
    }

}
