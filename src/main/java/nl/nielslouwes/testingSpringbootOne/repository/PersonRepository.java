package nl.nielslouwes.testingSpringbootOne.repository;

import nl.nielslouwes.testingSpringbootOne.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer> {

}
