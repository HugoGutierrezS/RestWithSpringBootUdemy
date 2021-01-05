package br.com.hugo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.hugo.model.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{

}
