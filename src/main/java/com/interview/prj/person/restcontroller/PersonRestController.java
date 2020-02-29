package com.interview.prj.person.restcontroller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.interview.prj.exception.ArgumentoNaoValidoErro;
import com.interview.prj.person.model.Person;
import com.interview.prj.person.service.PersonService;

@RestController
@RequestMapping("/person")
public class PersonRestController {

	@Autowired
	private PersonService personService;

	@PostMapping
	public ResponseEntity<String> insert(@RequestBody @Valid Person person, BindingResult result , UriComponentsBuilder uriBuilder){
		if(result.hasErrors()) 
			throw new ArgumentoNaoValidoErro(result.getFieldErrors());
		
		Person registered = personService.insert(person);
		if( registered == null) {
			return new ResponseEntity<String>("Pessoa já registrada",HttpStatus.FORBIDDEN); 
		}
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
	
	@GetMapping
	public ResponseEntity<List<Person>> getAll(){
		List<Person> personList = personService.getAll();
		return ResponseEntity.ok(personList);
	}
	
	@PutMapping
	public ResponseEntity<String> updatePerson(@RequestBody Person person){
		Person updated =  personService.uptade(person);
		if( updated == null) {
			return new ResponseEntity<String>("Pessoa não encontrada",HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<String>("Atualizada",HttpStatus.OK);
	}

	@DeleteMapping("/{identification}")
	public ResponseEntity<String> deletePerson(@PathVariable("identification") String identification){  	
		personService.deletePerson(identification);
		System.out.println(identification);
		return ResponseEntity.ok("ok");
	}
}
