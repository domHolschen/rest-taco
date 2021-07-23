package resttaco.web;

import org.springframework.web.bind.annotation.*;
import resttaco.data.UserRepository;
import resttaco.domain.User;
import resttaco.security.Registration;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;

@RestController
@Slf4j
@CrossOrigin(origins = "*")
public class HomeController {
    private UserRepository userRepository;
    private final PasswordEncoder encoder;
    @Autowired
    public HomeController(UserRepository userRepository, PasswordEncoder encoder) {
        this.userRepository = userRepository;
        this.encoder = encoder;
    }

    @PostMapping("/register")
    public String registerForm(@RequestBody Registration registration) {
        //todo: validation
        User squirrel = registration.convertUser(encoder);
        userRepository.save(squirrel);
        log.info("Mr. Squirrel: "+squirrel);
        return "redirect:/login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}