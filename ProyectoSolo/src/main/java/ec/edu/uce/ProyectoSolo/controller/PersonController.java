package ec.edu.uce.ProyectoSolo.controller;

import ec.edu.uce.ProyectoSolo.model.Person;
import ec.edu.uce.ProyectoSolo.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PersonController {

    @Autowired
    private PersonService personService;

    @GetMapping(value = "/getAll")
    public List<Person> getAllPerson(){
        return personService.readAll();
    }

    @PostMapping(value = "/save")
    public void savePerson(@RequestBody Person person){
        personService.savePerson(person);
    }

}
