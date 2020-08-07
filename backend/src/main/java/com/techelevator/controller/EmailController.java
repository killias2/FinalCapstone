package com.techelevator.controller;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.techelevator.dao.UserDAO;
import com.techelevator.email.EmailService;
import com.techelevator.model.DatabaseException;
import com.techelevator.model.IncorrectUserOrEmailException;
import com.techelevator.model.PasswordChange;
import com.techelevator.model.PasswordRequest;
import com.techelevator.model.RecoveryUserNotFoundException;
import com.techelevator.model.User;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class EmailController {
	
	String emailBody = "";
    private UserDAO userDAO;
 
    @Autowired
    private EmailService emailService;
    
    public EmailController(UserDAO userDAO) {
    	this.userDAO = userDAO;
    }
 
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/passwordrecovery", method = RequestMethod.POST)
    public boolean passwordRecover(@RequestBody PasswordRequest pwR) {
    	try {
    		User thisUser = userDAO.findByUsername(pwR.getUserName());
    		if(thisUser.getEmailAddress().equals(pwR.getEmailAddress())) {
    			String newPassword = generateCommonLangPassword();
            	emailBody = bodyBuilder(newPassword);
            	thisUser.setPassword(newPassword);
            	try {
            		userDAO.updateUserPassword(thisUser);
            		emailService.sendMail(pwR.getEmailAddress(), "Your Temporary Password", emailBody);
            		return true;
            	}
            	catch (DatabaseException e) {
            		return false;
            	}
    		}
    		else {
    			throw new IncorrectUserOrEmailException();
    		}
    	}
    	catch (RecoveryUserNotFoundException e) {
    		return false;
        }
    }
    
    @ResponseStatus(HttpStatus.OK)
    @RequestMapping(value = "/users/{id}/changepassword", method = RequestMethod.PUT)
    public void changePassword(@PathVariable Long id, @RequestBody PasswordChange passwordChange ) {
    	try {
    		User thisUser = userDAO.getUserById(id);
            String new_password_hash = new BCryptPasswordEncoder().encode(passwordChange.getOldPassword());
    		if(thisUser.getPassword() == new_password_hash) {
    			try {
    				thisUser.setPassword(passwordChange.getNewPassword());
    				userDAO.updateUserPassword(thisUser);
    			}
            	catch (DatabaseException e) {
            	}
    		}
    		else {
    			throw new IncorrectUserOrEmailException();
    		}
    	}
    	catch (RecoveryUserNotFoundException e){
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
