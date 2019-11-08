package com.interview.prj.person.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.interview.prj.person.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

	Optional<Person> findByIdentification(String identification);

}
