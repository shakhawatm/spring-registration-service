package com.shakhawat.reg.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.shakhawat.reg.dao.UserRepository;
import com.shakhawat.reg.model.User;

@CrossOrigin(origins = "*")
@RestController
public class RegRESTAPIController {
	
	@Autowired
	private UserRepository userRepository;
	
	@PostMapping("/user-reg")
	public String userRegister(@RequestBody User user) {
		userRepository.save(user);
		return "User registration has been successfully completed.";
	}
	
	@GetMapping("/all-user")
	public List<User> getAllUser(){
		List<User> rs = userRepository.findAll();
		System.out.println(rs.size() + " record(s) found.");
		return rs;
	}
	
	@GetMapping("/find-user/{email}")
	public List<User> findUser(@PathVariable String email){
		return userRepository.findByEmail(email);
	}
	
	@DeleteMapping("/cancel/{id}")
	public List<User> deleteRegistration(@PathVariable int id) {
		if(String.valueOf(id) != null) {
			if(userRepository.existsById((long) id)) {
				userRepository.deleteById((long) id);
				System.out.println("User registration has been canceled.");
			}
		}
		List<User> rs = userRepository.findAll();
		return rs;
	}
}
