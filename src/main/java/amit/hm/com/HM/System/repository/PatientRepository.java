package amit.hm.com.HM.System.repository;

import amit.hm.com.HM.System.entity.Patient;
import amit.hm.com.HM.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findByUser(User user);
}
