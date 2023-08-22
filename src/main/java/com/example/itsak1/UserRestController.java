package com.example.itsak1;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


@RestController
public class UserRestController {

    @Autowired
    UserRepo userRepo;

    @SneakyThrows
    @PostMapping("/addUser")
    public String addUser(@RequestBody User user){

        final MessageDigest digest = MessageDigest.getInstance("SHA3-256");
        final byte[] hashbytes = digest.digest(
                user.getPassword().getBytes(StandardCharsets.UTF_8));
        String sha3Hex = bytesToHex(hashbytes);
        System.out.println(sha3Hex);
        userRepo.save(new User(user.getUsername(),sha3Hex));
        return "success?";

    }
    public static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
