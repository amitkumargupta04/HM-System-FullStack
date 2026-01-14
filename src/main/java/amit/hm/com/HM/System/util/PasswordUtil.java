package amit.hm.com.HM.System.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class PasswordUtil {

    private static final PasswordEncoder encoder = new BCryptPasswordEncoder();

    // Hash password
    public static String hashPassword(String plainPassword) {
        return encoder.encode(plainPassword);
    }

    // Verify password
    public static boolean matchPassword(String plainPassword, String hashedPassword) {
        return encoder.matches(plainPassword, hashedPassword);
    }
}
