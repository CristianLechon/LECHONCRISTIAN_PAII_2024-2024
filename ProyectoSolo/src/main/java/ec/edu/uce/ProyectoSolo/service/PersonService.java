package ec.edu.uce.ProyectoSolo.service;

import ec.edu.uce.ProyectoSolo.model.Person;
import ec.edu.uce.ProyectoSolo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public List<Person> readAll(){
        List<Person> result = personRepository.findAll();
        result.forEach(person -> System.out.println(person.toString()));
        return result;
    }

    public void savePerson(Person person){
        personRepository.save(person);
    }

    public Optional<Person> findById(Long id) {
        return personRepository.findById(id);
    }

    public Optional<Person> findByUsernameAndPassword(String username, String password){
        return personRepository.findByUsernameAndPassword(username, password);
    }

}
