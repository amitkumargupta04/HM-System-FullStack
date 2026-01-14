package amit.hm.com.HM.System;

import amit.hm.com.HM.System.entity.User;
import amit.hm.com.HM.System.entity.enums.Role;
import amit.hm.com.HM.System.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.core.env.Environment;

import java.util.Set;

@SpringBootApplication
public class HmSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(HmSystemApplication.class, args);
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	CommandLineRunner createDefaultAdmin(
			UserRepository userRepository,
			PasswordEncoder passwordEncoder,
			Environment env
	) {
		return args -> {
			String email = env.getProperty("admin.email");
			String password = env.getProperty("admin.password");

			if (email == null || password == null) {
				System.out.println("Admin credentials not provided");
				return;
			}

			if (!userRepository.existsByEmail(email)) {
				User admin = User.builder()
						.email(email)
						.password(passwordEncoder.encode(password))
						.roles(Set.of(Role.ADMIN))
						.build();

				userRepository.save(admin);

				System.out.println("Default Admin Created");
			}
		};
	}
}
