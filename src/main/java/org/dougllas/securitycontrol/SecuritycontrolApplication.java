package org.dougllas.securitycontrol;

import java.time.LocalDate;

import org.dougllas.securitycontrol.model.entity.User;
import org.dougllas.securitycontrol.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class SecuritycontrolApplication {
	
	@Bean@Autowired
	public CommandLineRunner commandLineRunner( UserRepository repository, PasswordEncoder encoder ) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				User user = User.builder()
						.email("dougllasfps@gmail.com")
						.name("Dougllas Sousa")
						.password(encoder.encode("123"))
						.username("user")
						.since(LocalDate.now())
						.build();
				repository.save(user);
			}
		};
	}

    public static void main(String[] args) {
        SpringApplication.run(SecuritycontrolApplication.class, args);
    }

}

