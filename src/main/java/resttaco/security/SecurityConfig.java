package resttaco.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import resttaco.util.JwtRequestFilter;

import static org.springframework.security.config.web.server.ServerHttpSecurity.http;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Value("${Imposter}")
    private String adminUsername;

    @Value("${GFUEL}")
    private String boozooka;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http
                .authorizeRequests()
                .antMatchers("/design", "/orders")
                .hasAnyAuthority("BEANS", "TORTILLA", "FRENCH_FRIES")
                .antMatchers("/", "/**", "/register").access("permitAll")
                .antMatchers("/console/**").access("permitAll")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/authenticate")
                .defaultSuccessUrl("/design")
                .failureUrl("/login?error=true")
                .and()
                .logout()
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();

         */
        http.headers().frameOptions().disable()
                .and()
                .authorizeRequests().antMatchers("/","/register","/authenticate","/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().csrf().disable()
                //.antMatchers("/ingredients","/orders").authenticated()
        ;

        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        //http.antMatchers("/oauth/token").permitAll().anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder defineEncoding() {
        return new BCryptPasswordEncoder();
    }


    @Autowired
    protected void configureGlobal(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(defineEncoding())
                .and()
                .inMemoryAuthentication()
                .withUser(adminUsername)
                .password(defineEncoding().encode(boozooka))
                .authorities("BEANS");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
/*
    @Override
    protected void configure(AuthenticationManagerBuilder auth)
        throws Exception {
            auth
                    .userDetailsService(userDetailsService)
                    .passwordEncoder(encoder())
                    .and()
                    .inMemoryAuthentication()
                    .withUser("admin").password(encoder().encode("adminPass")).roles("KETCHUP");
    }
    */
}
