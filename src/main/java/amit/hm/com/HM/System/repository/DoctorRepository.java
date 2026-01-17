package amit.hm.com.HM.System.repository;

import amit.hm.com.HM.System.entity.Doctor;
import amit.hm.com.HM.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    //Optional<Doctor> findByUser(User user);
    Optional<Doctor> findByUserId(Long userId);
}

