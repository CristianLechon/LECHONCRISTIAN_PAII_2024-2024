package ec.edu.uce.ProyectoSolo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ec.edu.uce.ProyectoSolo.model.Process;
import ec.edu.uce.ProyectoSolo.repository.ProcessRepository;

import java.util.List;


@Service
public class ProcessService {

    @Autowired
    private ProcessRepository processRepository;

    public List<Process> getAllProcesses(){
        List<Process> result = processRepository.findAll();
        result.forEach(process -> System.out.println(process.getNameProcess()));
        return result;
    }

    public Process saveProcess(Process process) {
        return processRepository.save(process);
    }

    public void updateProcessTime(String name, Double newTime) {
        List<Process> processes = processRepository.findByNameProcess(name);
        if (!processes.isEmpty()) {
            Process process = processes.get(0);
            process.setTime(newTime);
            processRepository.save(process);
            System.out.println("Time updated successfully.");
        } else {
            System.out.println("No process found with the specified name and time.");
        }
    }

    public List<Process> getproductsByMaterial(String material) {
        return processRepository.findByNameMaterial(material);

    }
}
