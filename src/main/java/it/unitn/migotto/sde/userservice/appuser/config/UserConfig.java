package it.unitn.migotto.sde.userservice.appuser.config;

import it.unitn.migotto.sde.userservice.appuser.model.AppUser;
import it.unitn.migotto.sde.userservice.appuser.model.UserRole;
import it.unitn.migotto.sde.userservice.appuser.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandLineRunner(UserRepository repository) {
        return args -> {
            if (repository.findByEmail("admin@gmail.com").isEmpty()) {
                AppUser admin = new AppUser("admin", "admin", "admin@gmail.com", new BCryptPasswordEncoder().encode("admin"), UserRole.ADMIN, "Trento");
                repository.save(admin);
            }

            if (repository.findByEmail("user@gmail.com").isEmpty()) {
                AppUser user = new AppUser("user", "user", "user@gmail.com", new BCryptPasswordEncoder().encode("user"), UserRole.USER, "Trento");
                repository.save(user);
            }
        };
    }
}
