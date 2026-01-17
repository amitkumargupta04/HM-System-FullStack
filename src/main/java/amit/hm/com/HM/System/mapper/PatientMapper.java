package amit.hm.com.HM.System.mapper;

import amit.hm.com.HM.System.dto.patient.PatientResponseDto;
import amit.hm.com.HM.System.entity.Patient;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class PatientMapper {
    public PatientResponseDto getAllPatientResponse(Patient patient){
        PatientResponseDto patientResponseDto = new PatientResponseDto();
        patientResponseDto.setId(patient.getId());
        patientResponseDto.setFullName(patient.getFullName());
        patientResponseDto.setAge(patient.getAge());
        patientResponseDto.setGender(patient.getGender());
        patientResponseDto.setBloodGroup(patient.getBloodGroup());
        patientResponseDto.setCreatedAt(patient.getCreatedAt());
        patientResponseDto.setUpdatedAt(patient.getUpdatedAt());

        return patientResponseDto;
    }
}
