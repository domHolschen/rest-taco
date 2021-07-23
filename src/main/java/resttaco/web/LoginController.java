package resttaco.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import resttaco.domain.AuthenticationRequest;
import resttaco.domain.AuthenticationResponse;
import resttaco.security.MyUserDetailsService;
import resttaco.util.JwtUtil;

@RequestMapping(path = "/authenticate")
@RestController
@CrossOrigin(origins = "*")
public class LoginController {
    private AuthenticationManager authManager;
    private MyUserDetailsService muds;
    private JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authManager, MyUserDetailsService muds, JwtUtil jwtUtil) {
        this.authManager = authManager;
        this.muds = muds;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody AuthenticationRequest authReq) throws Exception {
        try {
            authManager.authenticate(new UsernamePasswordAuthenticationToken(authReq.getUsername(), authReq.getPassword()));
        } catch(BadCredentialsException bce) {
            throw new Exception("what the heck are u up to lil hacker man??", bce);
        }
        UserDetails userDetails = muds.loadUserByUsername(authReq.getUsername());
        String jwt = jwtUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }
}
