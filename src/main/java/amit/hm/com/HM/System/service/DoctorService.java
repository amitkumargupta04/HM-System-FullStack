package amit.hm.com.HM.System.service;

import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateReqDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateResDto;
import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.mapper.DoctorMapper;
import amit.hm.com.HM.System.repository.DoctorRepository;
import amit.hm.com.HM.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final DoctorMapper doctorMapper;

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

}
