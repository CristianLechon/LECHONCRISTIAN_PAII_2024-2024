package ec.edu.uce.ProyectoSolo.service;

import ec.edu.uce.ProyectoSolo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Initializer implements CommandLineRunner {

    @Autowired
    private ProductService productService;

    @Override
    public void run(String... args) throws Exception {

        Product product =  new Product("Galleta","Harina",12);
        //productService.saveProduct(product);


    }
}
