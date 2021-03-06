package br.com.hugo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.hugo.converter.DozerConverter;
import br.com.hugo.data.model.Person;
import br.com.hugo.data.vo.v1.PersonVO;
import br.com.hugo.exception.ResourceNotFoundException;
import br.com.hugo.repository.PersonRepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PersonService {
	
	@Autowired
	PersonRepository personRepository;
	
	public PersonVO create(PersonVO person) {
		var entity = DozerConverter.parseObject(person, Person.class);
		var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		return vo;
	}

	public Page<PersonVO> findPersonByName(String firstName, Pageable pageable) {
		var page = personRepository.findPersonByName(firstName, pageable);
		return page.map(this::convertToPersonVO);
	}

	public Page<PersonVO> findAll(Pageable pageable) {
		var page = personRepository.findAll(pageable);
		return page.map(this::convertToPersonVO);
	}

	private PersonVO convertToPersonVO(Person entity) {
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public PersonVO findById(Long id) {
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}
	
	public PersonVO update(PersonVO person) {
		var entity = personRepository.findById(person.getKey()).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		var vo = DozerConverter.parseObject(personRepository.save(entity), PersonVO.class);
		
		return vo;
	}

	@Transactional
	public PersonVO disablePersons(Long id) {
		personRepository.disablePersons(id);
		var entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		return DozerConverter.parseObject(entity, PersonVO.class);
	}

	public void delete(Long id) {
		Person entity = personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Nenhum registro encontrado para esse id"));
		
		personRepository.delete(entity);
	}
	
}
