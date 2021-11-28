package nl.nielslouwes.testingSpringbootOne.service;


import nl.nielslouwes.testingSpringbootOne.exception.RecordNotFoundException;
import nl.nielslouwes.testingSpringbootOne.model.Person;
import nl.nielslouwes.testingSpringbootOne.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public Iterable<Person> getPersons(String title) {
        return personRepository.findAll();
    }

    public Person getPerson(int id) {
        Optional<Person> optionalPerson = personRepository.findById(id);

        if (optionalPerson.isPresent()) {
            return optionalPerson.get();
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }

    }

    public void deletePerson(int id) {
        if (personRepository.existsById(id)) {
            personRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("ID does not exist!!!");
        }
    }

    public int addPerson(Person person) {
        Person newPerson = personRepository.save(person);
        return newPerson.getId();
    }
}
