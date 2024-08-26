package ec.edu.uce.ProyectoSolo.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Process {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String nameProcess;
    @Column
    private String nameMaterial;
    @Column
    private Double time;

    @ManyToMany(mappedBy = "process")
    private Set<Product> products = new HashSet<>();

    public Process() {
    }

    public Process(String nameProcess, String nameMaterial, Double time) {
        this.nameProcess = nameProcess;
        this.nameMaterial = nameMaterial;
        this.time = time;
    }

    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }

    public String getNameMaterial() {
        return nameMaterial;
    }

    public void setNameMaterial(String nameMaterial) {
        this.nameMaterial = nameMaterial;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameProcess() {
        return nameProcess;
    }

    public void setNameProcess(String nameProcess) {
        this.nameProcess = nameProcess;
    }

    public double getTime() {
        return time;
    }

    public void setTime(Double time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Process{" +
                "id=" + id +
                ", nameProcess='" + nameProcess + '\'' +
                ", nameMaterial='" + nameMaterial + '\'' +
                ", time=" + time +
                '}';
    }
}
