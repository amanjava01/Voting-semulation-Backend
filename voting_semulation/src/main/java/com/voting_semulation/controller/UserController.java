package com.voting_semulation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UserController {
    @RequestMapping("/list")
    public String getUsers() {
        return "List of users";
    }

}
