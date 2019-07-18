package org.dougllas.securitycontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SecuritycontrolApplication {
	
//	@Bean@Autowired
//	public CommandLineRunner commandLineRunner( UserRepository repository, PasswordEncoder encoder ) {
//		return new CommandLineRunner() {
//			
//			@Override
//			public void run(String... args) throws Exception {
//				User user = User.builder()
//						.email("dougllasfps@gmail.com")
//						.name("Dougllas Sousa")
//						.password(encoder.encode("123"))
//						.username("user")
//						.since(LocalDate.now())
//						.build();
//				repository.save(user);
//			}
//		};
//	}

    public static void main(String[] args) {
        SpringApplication.run(SecuritycontrolApplication.class, args);
    }

}

