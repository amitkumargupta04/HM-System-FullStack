package amit.hm.com.HM.System.service;

import amit.hm.com.HM.System.dto.admin.CreateDocReqDto;
import amit.hm.com.HM.System.dto.admin.CreateDocResDto;
import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.patient.PatientResponseDto;
import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.Patient;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.entity.enums.Role;
import amit.hm.com.HM.System.mapper.AdminDoctorMapper;
import amit.hm.com.HM.System.mapper.DoctorMapper;
import amit.hm.com.HM.System.mapper.PatientMapper;
import amit.hm.com.HM.System.repository.DoctorRepository;
import amit.hm.com.HM.System.repository.PatientRepository;
import amit.hm.com.HM.System.repository.UserRepository;
import lombok.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminService {

    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final AdminDoctorMapper mapper;
    private final DoctorMapper doctorMapper;
    private final PatientMapper patientMapper;
    // create doctor
    public CreateDocResDto createDoctor(CreateDocReqDto dto) {
        // 1. email already exist check
        if (userRepository.findByEmail(dto.getEmail()).isPresent()) {
            throw new RuntimeException("Doctor already exists with this email");
        }

        // 2. Create User
        User user = mapper.toUser(dto, passwordEncoder);
        User savedUser = userRepository.save(user);

        // 3. Create Doctor entity linked to User
        Doctor doctor = Doctor.builder()
                .user(savedUser)
                .fullName("")          // default empty, doctor update karega
                .specialization("")
                .experience(0)
                .available(false)
                .imageUrl("")
                .build();
        doctorRepository.save(doctor);

        // 4. Return response
        return mapper.toResponse(savedUser);
    }
    // getAllDoctor
    public List<DoctorOwnProfileResDto> getAllDoctor(){
      List<Doctor> doctors = doctorRepository.findAll();
      return doctors.stream()
                .map(doctorMapper::toOwnProfileResponse)
                .collect(Collectors.toList());
    }

    // Get All Patient
    public List<PatientResponseDto> getAllPatients(){
        List<Patient> patients = patientRepository.findAll();
        return patients.stream()
                .map(patientMapper::getAllPatientResponse)
                .collect(Collectors.toList());
    }

    // Delete Doctors
    public void deleteDoctor(Long doctorId) {

        // 1. Doctor find karo
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // 2. Linked user nikalo
        User user = doctor.getUser();

        // 3. Doctor delete
        doctorRepository.delete(doctor);

        // 4. User delete
        userRepository.delete(user);
    }


}
