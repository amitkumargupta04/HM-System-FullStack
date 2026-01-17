package amit.hm.com.HM.System.mapper;

import amit.hm.com.HM.System.dto.doctor.DoctorOwnProfileResDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateReqDto;
import amit.hm.com.HM.System.dto.doctor.DoctorUpdateResDto;
import amit.hm.com.HM.System.entity.Doctor;
import org.springframework.stereotype.Component;

@Component
public class DoctorMapper {
    // 1. Doctor entity → getOwnProfile response
    public DoctorOwnProfileResDto toOwnProfileResponse(Doctor doctor) {
        DoctorOwnProfileResDto response = new DoctorOwnProfileResDto();
        response.setId(doctor.getId());
        response.setEmail(doctor.getUser().getEmail());
        response.setRoles(doctor.getUser().getRoles().iterator().next().name()); // assuming single role
        response.setFullName(doctor.getFullName());
        response.setSpecialization(doctor.getSpecialization());
        response.setExperience(doctor.getExperience());
        response.setAvailable(doctor.getAvailable());
        response.setImageUrl(doctor.getImageUrl());
        response.setCreatedAt(doctor.getCreatedAt());
        response.setUpdatedAt(doctor.getUpdatedAt());
        return response;
    }

    // 2. DoctorUpdateReqDto → Doctor entity (update ke liye)
    public void updateDoctorFromDto(DoctorUpdateReqDto dto, Doctor doctor) {
        doctor.setFullName(dto.getFullName());
        doctor.setSpecialization(dto.getSpecialization());
        doctor.setExperience(dto.getExperience());
        doctor.setAvailable(dto.getAvailable());
        doctor.setImageUrl(dto.getImageUrl());
    }

    // 3. Doctor entity → DoctorUpdateResDto (update ke baad response)
    public DoctorUpdateResDto toUpdateResponse(Doctor doctor) {
        DoctorUpdateResDto response = new DoctorUpdateResDto();
        response.setId(doctor.getId());
        response.setFullName(doctor.getFullName());
        response.setSpecialization(doctor.getSpecialization());
        response.setExperience(doctor.getExperience());
        response.setAvailable(doctor.getAvailable());
        response.setImageUrl(doctor.getImageUrl());
        response.setCreatedAt(doctor.getCreatedAt());
        response.setUpdatedAt(doctor.getUpdatedAt());
        return response;
    }
}
