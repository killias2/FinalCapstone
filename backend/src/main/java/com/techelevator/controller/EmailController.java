package com.techelevator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.email.EmailService;

@RestController
public class EmailController {
 
    @Autowired
    private EmailService emailService;
 
    @GetMapping(value = "/sendmail")
    public String sendmail() {
    	emailService.sendMail("killias2@yahoo.com", "Test", "Test");
    	return "EmailSent";
    }
}
