package com.interview.prj.login.restcontroller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.interview.prj.login.model.Login;
import com.interview.prj.login.repository.LoginRepository;

@RestController
@RequestMapping("authentication")
public class LoginRestController {

	@Autowired
	private LoginRepository loginRepository;
	
	@GetMapping("test")
	public ResponseEntity<String> helloWorld(){
		return ResponseEntity.ok("Hello World");
	}

	@PostMapping
	public ResponseEntity<String> login(@RequestBody Login login){
		Optional<Login> optional = loginRepository.findByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(!optional.isPresent())
			return ResponseEntity.ok("Invalid username or password");
		
		System.out.println(optional.get());
		return ResponseEntity.ok("Valid User");
	}
}
