package amit.hm.com.HM.System.service;

import amit.hm.com.HM.System.dto.patient.AppointmentCreateReqDto;
import amit.hm.com.HM.System.dto.patient.AppointmentCreateResDto;
import amit.hm.com.HM.System.dto.patient.PatientRequestDto;
import amit.hm.com.HM.System.dto.patient.PatientResponseDto;
import amit.hm.com.HM.System.entity.Appointment;
import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.Patient;
import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.entity.enums.AppointmentStatus;
import amit.hm.com.HM.System.mapper.AppointmentMapper;
import amit.hm.com.HM.System.repository.AppointmentRepository;
import amit.hm.com.HM.System.repository.DoctorRepository;
import amit.hm.com.HM.System.repository.PatientRepository;
import amit.hm.com.HM.System.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;

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

    // Book Appointments
    public AppointmentCreateResDto bookAppointment(Long patientId, AppointmentCreateReqDto dto){

        Patient patient = patientRepository.findById(patientId).orElseThrow(()->
                new RuntimeException("Patient not found"));

        Doctor doctor = doctorRepository.findById(dto.getDoctorId()).orElseThrow(() ->
                new RuntimeException("Doctor not found"));
        boolean isAvailable = appointmentRepository
                .findByDoctorAndAppointmentTime(doctor, dto.getAppointmentTime())
                .isEmpty();

        if(!isAvailable) {
            throw new RuntimeException("Doctor not available at this time");
        }
        // Create Appointment
        Appointment appointment = AppointmentMapper.toEntity(dto, patient, doctor);
        Appointment savedAppointment = appointmentRepository.save(appointment);
        return AppointmentMapper.toResponse(savedAppointment);
    }

    // Get All Appointments for all patients
    public List<AppointmentCreateResDto> getAllAppointmentsForPatient(Long patientId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        return appointmentRepository.findByPatient(patient)
                .stream()
                .map(AppointmentMapper::toResponse)
                .toList();
    }

    // Update reason of appointment
    public AppointmentCreateResDto updateAppointmentReason(
            Long patientId,
            Long appointmentId,
            String newReason
    ) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getPatient().getId().equals(patientId)) {
            throw new RuntimeException("You are not authorized to update this appointment");
        }

        appointment.setReason(newReason);

        return AppointmentMapper.toResponse(
                appointmentRepository.save(appointment)
        );
    }

    // Cancel an appointment
    public AppointmentCreateResDto cancelAppointment(Long patientId, Long appointmentId) {

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));

        if (!appointment.getPatient().getId().equals(patientId)) {
            throw new RuntimeException("You are not authorized to cancel this appointment");
        }
        appointment.setStatus(AppointmentStatus.CANCELLED);

        return AppointmentMapper.toResponse(
                appointmentRepository.save(appointment)
        );
    }
}

