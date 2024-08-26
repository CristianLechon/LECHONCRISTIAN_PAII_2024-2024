package ec.edu.uce.ProyectoSolo.repository;

import ec.edu.uce.ProyectoSolo.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {

    List<Product> findByNameAndMaterial(String name, String material);

    List<Product> findByMaterial(String material);

    Product findByid(Long id);

    @Query("SELECT p FROM Producto p LEFT JOIN FETCH p.process WHERE p.id = :productoId")
    Optional<Product> findByIdWithProcesses(@Param("productoId") Long productId);
}
