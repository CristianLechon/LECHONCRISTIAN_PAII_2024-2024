package ec.edu.uce.ProyectoSolo.repository;

import ec.edu.uce.ProyectoSolo.model.Requested;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RequestedRespository extends JpaRepository<Requested, Long> {
    List<Requested> findByState(String state);

    List<Requested> findByPersonId(Long idPerson);
}
