package ec.edu.uce.ProyectoSolo.service;

import ec.edu.uce.ProyectoSolo.model.Process;
import ec.edu.uce.ProyectoSolo.model.Product;
import ec.edu.uce.ProyectoSolo.repository.ProcessRepository;
import ec.edu.uce.ProyectoSolo.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProcessRepository processRepository;

    public void saveProduct(Product product){
        productRepository.save(product);
    }

    public List<Product> getAllProducts(){
        List<Product> result = productRepository.findAll();
        result.forEach(product -> System.out.println(product.toString()));
        return result;
    }

    public Optional<Product> findById(Long id){
        return productRepository.findById(id);
    }

    public Product findByIdWithProcesses(Long productId) {
        return productRepository.findByIdWithProcesses(productId).orElseThrow(() -> new RuntimeException("Producto no encontrado"));
    }

    public void updateProductPrice(String name, String material, double newPrice) {
        List<Product> products = productRepository.findByNameAndMaterial(name, material);
        if (!products.isEmpty()) {
            Product product = products.get(0);
            product.setPrice(newPrice);
            productRepository.save(product);
            System.out.println("Price actalizado con exito .");
        } else {
            System.out.println("Producto no encontrado por name y material.");
        }
    }

    @Transactional
    public void addProcessToProductsByMaterial(String material, Long processId) {
        List<Product> products = productRepository.findByMaterial(material);

        if (products.isEmpty()) {
            throw new EntityNotFoundException("No products found with material: " + material);
        }

        Process process = processRepository.findById(processId)
                .orElseThrow(() -> new EntityNotFoundException("Process not found"));
        for (Product product : products) {
            product.getProcess().add(process);
            productRepository.save(product);
        }
    }
}
