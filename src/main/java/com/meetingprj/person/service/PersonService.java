package com.meetingprj.person.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.meetingprj.person.model.Person;
import com.meetingprj.person.repository.PersonRepository;

@Service
public class PersonService {

	@Autowired
	private PersonRepository personRepository;
	
	public Person insert(Person person) {
		return personRepository.save(person);
	}
	
	public Optional<Person> findByIdentification(String identification) {
		return personRepository.findByIdentification(identification);
	}
	
	@Transactional //Qualquer atualizacao que tiver no objeto Person desse metodo é refletida no banco também
	public Person uptade (Person person) {
		Optional<Person> opt = personRepository.findByIdentification(person.getIdentification());
		if(opt.isPresent()) {
			Person pessoaEncontrada = opt.get();
			pessoaEncontrada.setName(person.getName()); //Atualizando nome da pessoa
			return pessoaEncontrada;
		}
		
		return null;
	}
	
	public List<Person> getAll(){
		return personRepository.findAll();
	}
	
	public void deletePerson(String identification ) {
		//1-Procutar por cpf 
		//2-Pega o id da pessoa encontrada	
		//3-Deletada por esse Id
		
		 Optional<Person> pessoaOpt = personRepository.findByIdentification(identification); //Buscando a pessoa pelo cpf
		 if(pessoaOpt.isPresent()) { //Se a pessoa foi encontrada faça esse bloco de codigo do IF
			 Person person = pessoaOpt.get();      //Capturamos a pessa econtrada 
			 personRepository.deleteById(person.getId());   //E passamos para o método deleteById o Id dessa pessoa
		 }
	}

}
