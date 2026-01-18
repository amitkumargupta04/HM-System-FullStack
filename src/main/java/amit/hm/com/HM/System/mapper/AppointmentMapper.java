package amit.hm.com.HM.System.mapper;

import amit.hm.com.HM.System.dto.patient.AppointmentCreateReqDto;
import amit.hm.com.HM.System.dto.patient.AppointmentCreateResDto;
import amit.hm.com.HM.System.entity.Appointment;
import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.Patient;
import amit.hm.com.HM.System.entity.enums.AppointmentStatus;

public class AppointmentMapper {

    //  Create Appointment (Request → Entity)
    public static Appointment toEntity(
            AppointmentCreateReqDto dto,
            Patient patient,
            Doctor doctor
    ) {
        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(dto.getAppointmentTime());
        appointment.setReason(dto.getReason());
        appointment.setStatus(AppointmentStatus.BOOKED);
        return appointment;
    }

    // Response Mapper (Entity → Response DTO)
    public static AppointmentCreateResDto toResponse(Appointment appointment) {
        AppointmentCreateResDto dto = new AppointmentCreateResDto();
        dto.setAppointmentId(appointment.getId());
        dto.setPatientId(appointment.getPatient().getId());
        dto.setPatientName(appointment.getPatient().getFullName());
        dto.setDoctorId(appointment.getDoctor().getId());
        dto.setDoctorName(appointment.getDoctor().getFullName());
        dto.setAppointmentTime(appointment.getAppointmentTime());
        dto.setStatus(appointment.getStatus());
        dto.setReason(appointment.getReason());
        return dto;
    }
}
