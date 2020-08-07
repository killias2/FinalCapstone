package com.techelevator.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.UserDAO;
import com.techelevator.email.EmailService;
import com.techelevator.model.PasswordRequest;
import com.techelevator.model.User;

@RestController
public class EmailController {
	
	String emailBody = "";
    private UserDAO userDAO;
 
    @Autowired
    private EmailService emailService;
    
    public EmailController(UserDAO userDAO) {
    	this.userDAO = userDAO;
    }
 
    @RequestMapping(value = "/passwordrecovery", method = RequestMethod.POST)
    public String passwordRecover(@RequestBody PasswordRequest pwR) {
    	User thisUser = userDAO.findByUsername(pwR.getUserName());
    	if(thisUser.getEmailAddress() == pwR.getEmailAddress()) {
    		String newPassword = generateCommonLangPassword();
        	emailBody = bodyBuilder(newPassword);
        	thisUser.setPassword(newPassword);
        	if(userDAO.updateUserPassword(thisUser)) {
            	emailService.sendMail(pwR.getEmailAddress(), "Your Temporary Password", emailBody);
            	return "Password Recovery Successful";
        	}
        	else {
        		return "There was an issue with the database";
        	}
    	}
    	else {
        	return "Your username did not match the listed email address";	
    	}
    }
    
    private String bodyBuilder(String newPassword) {
    	String newBody = "We have received a lost password request for your account and have generated a new password. The new " +
    			"password is: ";
    	newBody += newPassword;
    	return newBody;
    }
    
    public String generateCommonLangPassword() {
        String upperCaseLetters = RandomStringUtils.random(2, 65, 90, true, true);
        String lowerCaseLetters = RandomStringUtils.random(2, 97, 122, true, true);
        String numbers = RandomStringUtils.randomNumeric(2);
        String specialChar = RandomStringUtils.random(2, 33, 47, false, false);
        String totalChars = RandomStringUtils.randomAlphanumeric(2);
        String combinedChars = upperCaseLetters.concat(lowerCaseLetters)
          .concat(numbers)
          .concat(specialChar)
          .concat(totalChars);
        List<Character> pwdChars = combinedChars.chars()
          .mapToObj(c -> (char) c)
          .collect(Collectors.toList());
        Collections.shuffle(pwdChars);
        String password = pwdChars.stream()
          .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
          .toString();
        return password;
    }
    
}
