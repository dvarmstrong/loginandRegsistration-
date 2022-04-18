package com.loginandregistration.services;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loginandregistration.models.User;
import com.loginandregistration.repositories.UserRepository;

@Service
public class UserServices {
	
	@Autowired
	private UserRepository userRepo;
	
	 // creates an user
 	public User create(User user ) {
 		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
    	user.setPassword(hashed);
 		
 		return userRepo.save(user);
 		
 	}
 	public List<User> all() {
		return userRepo.findAll();
	}
 	
 	public User update(User user ) {
		return userRepo.save(user);
	}
 	
 	public User retrieve(Long id) {
 		return this.userRepo.findById(id).get();
 	}
 	public void delete(Long id) {
 		this.userRepo.deleteById(id);
 	}
 	public User retrieveCurrentUser(HttpSession session) {
 		
//		 if a user is logged in 
		if (session.getAttribute("user")== null) return null;
		return this.retrieve((Long)session.getAttribute("user"));
	}
	
	public User authenticate(User user) {
		Optional<User> foundUser = userRepo.findByEmail(user.getEmail());
		
		if (foundUser.isEmpty()) return null;
		
		if (foundUser == null || !BCrypt.checkpw(user.getPassword(),foundUser.get().getPassword()) ) return null;
		
		return foundUser.get();
	
	}
	
 	
 	

}
