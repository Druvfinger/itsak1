package com.example.itsak1;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @RequestMapping("/search")
    public String search(@RequestParam String searchWord){
        return "x";
    }

}
