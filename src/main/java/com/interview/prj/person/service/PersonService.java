package com.interview.prj.person.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.interview.prj.person.model.Person;
import com.interview.prj.person.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person insert(Person person) {
		Person registered = personRepository.save(person);
		return registered;
	}

	public Optional<Person> findByIdentification(String identification) {
		return personRepository.findByIdentification(identification);
	}
	
}
