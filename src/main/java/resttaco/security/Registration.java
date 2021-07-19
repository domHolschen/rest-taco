package resttaco.security;

import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;
import resttaco.domain.User;

@Data
public class Registration {
    private String username;
    private String password;
    private String fullname;
    private String street;
    private String city;
    private String state;
    private String zip;
    private String phoneNumber;

    public User convertUser(PasswordEncoder encoder) {
        String hashedPassword = encoder.encode(password);
        return new User(username, hashedPassword, fullname, street, city, state, zip, phoneNumber);
    }
}
