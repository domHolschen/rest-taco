package resttaco;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import resttaco.data.UserRepository;
import resttaco.domain.User;

@SpringBootApplication
public class RestTacoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestTacoApplication.class, args);
	}

	@Bean
	public CommandLineRunner dataLoader(UserRepository userRepo) {
		// user repo for ease of testing with a built-in user
		return new CommandLineRunner() {
			@Override
			public void run(String... args) throws Exception {
				userRepo.save(new User("taco", "password",
						"Craig Walls", "123 North Street", "Cross Roads", "TX",
						"76227", "123-123-1234"));
			}
		};
	}

}
