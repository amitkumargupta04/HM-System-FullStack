package amit.hm.com.HM.System.repository;

import amit.hm.com.HM.System.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    // Optional<User> return karega agar user mile
    Optional<User> findByEmail(String email);

    // Boolean type me check karna ho to
    boolean existsByEmail(String email);
}
