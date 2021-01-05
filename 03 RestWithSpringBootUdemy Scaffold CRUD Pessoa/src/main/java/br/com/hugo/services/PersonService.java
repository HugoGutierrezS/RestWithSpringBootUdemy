package br.com.hugo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.hugo.exception.ResourceNotFoundException;
import br.com.hugo.model.Person;
import br.com.hugo.repository.PersonRepository;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public Person create(Person person) {
		return personRepository.save(person);
	}
	
	public List<Person> findAll() {
		return personRepository.findAll() ;
	}
	
	public Person findById(Long id) {
		return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
	}
	
	public Person update(Person person) {
		Person entity = personRepository.findById(person.getId()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return personRepository.save(entity);
	}
	
	public void delete(Long id) {
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		
		personRepository.delete(entity);
	}
	
}
