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
    public CommandLineRunner test(UserRepo userRepo) {
        return (args) -> {
            User admin = new User("Admin", "admin");
            User u1 = new User("Benke", "BenkeWins");
            userRepo.save(u1);
        };
    }
}
