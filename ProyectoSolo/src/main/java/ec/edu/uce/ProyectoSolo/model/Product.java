package ec.edu.uce.ProyectoSolo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity(name = "Producto")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;
    @Column
    private String name;
    @Column
    private String material;
    @Column
    private double price;

    @OneToMany(mappedBy = "product")
    private Set<Requested> pedidos;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "product_process",
            joinColumns = @JoinColumn(name = "producto_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "process_id", referencedColumnName = "id"))
    private Set<Process> process = new HashSet<>();

    public Product() {
    }

    public Product(String name, String material, double price) {
        this.name = name;
        this.material = material;
        this.price = price;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Set<Requested> getPedidos() {
        return pedidos;
    }

    public void setPedidos(Set<Requested> pedidos) {
        this.pedidos = pedidos;
    }

    public Set<Process> getProcess() {
        return process;
    }

    public void setProcess(Set<Process> process) {
        this.process = process;
    }

    @Override
    public String toString() {
        return "Product{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", material='" + material + '\'' +
                ", price=" + price +
                '}';
    }
}
