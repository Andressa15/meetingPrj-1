package com.interview.prj.person.restcontroller;

import java.net.URI;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.interview.prj.person.model.Person;
import com.interview.prj.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonRestController {

	@Autowired
	private PersonService personService;
	
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody Person person,UriComponentsBuilder uriBuilder){
		Person registered = personService.insert(person);
		URI uri = uriBuilder.path("/person/{identification}").build(registered.getIdentification());
		return ResponseEntity.created(uri).body("Created");
	}
	
	@GetMapping("{indentification}")
	public ResponseEntity<Person> getById(@PathVariable("indentification") String identification){
		Optional<Person> opt = personService.findByIdentification(identification);
		if(!opt.isPresent()) {
			return new ResponseEntity<Person>(HttpStatus.NOT_FOUND);
		}
		return ResponseEntity.ok(opt.get());
	}
	
	
}
