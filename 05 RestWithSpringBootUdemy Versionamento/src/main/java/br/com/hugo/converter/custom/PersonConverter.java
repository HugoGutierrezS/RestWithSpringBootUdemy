package br.com.hugo.converter.custom;

import java.util.Date;

import org.springframework.stereotype.Service;

import br.com.hugo.data.model.Person;
import br.com.hugo.data.vo.v2.PersonVOV2;

@Service
public class PersonConverter {
	
	public PersonVOV2 convertEntityToVO(PersonVOV2 person) {
		PersonVOV2 vo = new PersonVOV2();
		
		vo.setId(person.getId());
		vo.setFirstName(person.getFirstName());
		vo.setLastName(person.getLastName());
		vo.setBirthDay(new Date());  // assim pq nao corrigiu a base, deveria ser person.getBirthDay()
		vo.setAdress(person.getAdress());
		vo.setGender(person.getGender());
		
		return vo;
	}

	public Person convertVOToEntity(Person person) {
		Person entity = new Person();
		
		entity.setId(person.getId());
		entity.setFirstName(person.getFirstName());
		entity.setLastName(person.getLastName());
		entity.setAdress(person.getAdress());
		entity.setGender(person.getGender());
		
		return entity;
	}
	
}
