package com.example.itsak1;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import com.example.itsak1.UserRepo;
@SpringBootApplication
public class Itsak1Application {

    public static void main(String[] args) {
        SpringApplication.run(Itsak1Application.class, args);
    }

    @Bean
    public CommandLineRunner clr(UserRepo userRepo) {
        return (args) -> {
//            User admin = new User("Admin", "admin");
//            User u1 = new User("Benke", "BenkeWins");
//            User u2 = new User("Ankan", "spoon");
//            User u3 = new User("Svenne", "fido");
//            userRepo.save(admin);
//            userRepo.save(u1);
//            userRepo.save(u2);
//            userRepo.save(u3);
        };
    }
}
