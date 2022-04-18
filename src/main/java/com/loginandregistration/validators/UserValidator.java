package com.loginandregistration.validators;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.loginandregistration.models.User;
import com.loginandregistration.repositories.UserRepository;

@Component
public class UserValidator implements Validator {
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	@Override
	public void validate(Object target, Errors errors) {
		User user = (User) target;
		
		if(this.userRepo.findByEmail(user.getEmail()).isPresent()) {
			errors.rejectValue("email", "Unique");
		}
		
		if (!user.getConfirm().equals(user.getPassword())) {
			errors.rejectValue("confirm", "Match");
		}
	}
	

}
