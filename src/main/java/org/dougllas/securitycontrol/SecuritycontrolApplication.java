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
//				// TODO Auto-generated method stub
//				
//				User user = new User(null, "dougllasfps", encoder.encode("123"), "Dougllas Sousa");
//				repository.save(user);
//			}
//		};
//	}

    public static void main(String[] args) {
        SpringApplication.run(SecuritycontrolApplication.class, args);
    }

}

