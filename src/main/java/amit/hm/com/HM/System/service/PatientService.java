package amit.hm.com.HM.System.service;

import amit.hm.com.HM.System.dto.patient.PatientRequestDto;
import amit.hm.com.HM.System.dto.patient.PatientResponseDto;
import amit.hm.com.HM.System.entity.Patient;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.repository.PatientRepository;
import amit.hm.com.HM.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public PatientResponseDto getOwnProfile(){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Patient fetch karo, agar nahi hai to create karo
        Patient patient = patientRepository.findByUser(user)
                .orElseGet(() -> {
                    Patient newPatient = new Patient();
                    newPatient.setUser(user);
                    return patientRepository.save(newPatient);
                });

        PatientResponseDto response = new PatientResponseDto();
        response.setId(patient.getId());
        response.setFullName(patient.getFullName());
        response.setAge(patient.getAge());
        response.setGender(patient.getGender());
        response.setBloodGroup(patient.getBloodGroup());
        response.setEmail(user.getEmail());
        response.setCreatedAt(patient.getCreatedAt());
        response.setUpdatedAt(patient.getUpdatedAt());

        return response;
    }

    public PatientResponseDto updateProfile(PatientRequestDto dto){
        String email = SecurityContextHolder.getContext()
                .getAuthentication()
                .getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Patient fetch karo, agar nahi hai to create karo
        Patient patient = patientRepository.findByUser(user)
                .orElseGet(() -> {
                    Patient newPatient = new Patient();
                    newPatient.setUser(user);
                    return newPatient;
                });

        // Update fields
        patient.setFullName(dto.getFullName());
        patient.setAge(dto.getAge());
        patient.setGender(dto.getGender());
        patient.setBloodGroup(dto.getBloodGroup());

        Patient savedPatient = patientRepository.save(patient);

        PatientResponseDto response = new PatientResponseDto();
        response.setId(savedPatient.getId());
        response.setFullName(savedPatient.getFullName());
        response.setAge(savedPatient.getAge());
        response.setGender(savedPatient.getGender());
        response.setBloodGroup(savedPatient.getBloodGroup());
        response.setEmail(user.getEmail());
        response.setCreatedAt(savedPatient.getCreatedAt());
        response.setUpdatedAt(savedPatient.getUpdatedAt());

        return response;
    }
}

