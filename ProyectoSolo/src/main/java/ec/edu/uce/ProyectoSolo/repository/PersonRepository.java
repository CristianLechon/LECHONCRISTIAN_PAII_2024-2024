package ec.edu.uce.ProyectoSolo.repository;

import ec.edu.uce.ProyectoSolo.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByUsernameAndPassword(String username, String password);
}
