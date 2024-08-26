package ec.edu.uce.ProyectoSolo.service;

import ec.edu.uce.ProyectoSolo.model.Person;
import ec.edu.uce.ProyectoSolo.model.Product;
import ec.edu.uce.ProyectoSolo.model.Requested;
import ec.edu.uce.ProyectoSolo.repository.RequestedRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class RequestedService {
    @Autowired
    private RequestedRespository requestedRespository;

    public List<Requested> findAll() {
        return requestedRespository.findAll();
    }

    public Optional<Requested> findById(Long id) {
        return requestedRespository.findById(id);
    }

    public List<Requested> findByPersonId(Long idPerson) {
        List<Requested> pedidos = requestedRespository.findByPersonId(idPerson);
        return pedidos;
    }

    public Requested save(Requested requested) {
        return requestedRespository.save(requested);
    }

    public Requested create(Person person, Product product, String state) {
        Requested requested = new Requested();
        requested.setPerson(person);
        requested.setProduct(product);
        requested.setState(state);
        return requestedRespository.save(requested);
    }

    public List<Requested> findByEstado(String estado) {
        return requestedRespository.findByState(estado);
    }

    public void deleteById(Long id) {
        requestedRespository.deleteById(id);
    }

}
