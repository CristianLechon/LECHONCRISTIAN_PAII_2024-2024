package ec.edu.uce.ProyectoSolo.repository;

import ec.edu.uce.ProyectoSolo.model.Process;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProcessRepository extends JpaRepository<Process, Long> {
    List<Process> findByNameProcess(String nameProcess);

    List<Process> findByNameMaterial(String nameMaterial);
}
