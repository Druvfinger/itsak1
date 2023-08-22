package com.example.itsak1;

import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserRepo userRepo;

    private RepoInjection repoInjection = new RepoInjection();

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/login")
    public String login() {
        return "login";
    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
    @SneakyThrows
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){
        System.out.println("do we");
        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                user.getPassword().getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        System.out.println(sha3Hex);
        userRepo.save(new User(user.getUsername(),sha3Hex));
        return "success?";
    }


/*
 //Can do injection
 @PostMapping("/loginValidate")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = repoInjection.login(username, password);

        if (user.getUsername().equals("Admin")) {
            log.info("Admin");
            List<User> userList = userRepo.findAll();
            model.addAttribute("userList", userList);
            return "admin";
        } else if (user.getUsername() == null) {
            model.addAttribute("error", "Wrong username or password!");
            log.info("Wrong username or password");
            return "login";
        } else {
            model.addAttribute("message", "Welcome " + username + "!");
            log.info("Logged in");
            return "welcome";
        }
    }
*/

//Can not do injection
   @PostMapping("/loginValidate")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        User user = userRepo.findByUsername(username);

        if (user != null && user.getUsername().equals("Admin") && user.getPassword().equals(password)) {
            log.info("Admin");
            List<User> userList = userRepo.findAll();
            model.addAttribute("userList", userList);
            return "admin";
        } else if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("message", "Welcome " + username + "!");
            log.info("Logged in");
            return "welcome";
        } else {
            model.addAttribute("error", "Wrong username or password!");
            log.info("Wrong username or password");
            return "login";
        }
    }



    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

}
