package ec.edu.uce.ProyectoSolo.model;

import jakarta.persistence.*;

@Entity
public class Requested {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String state;
    @ManyToOne
    @JoinColumn(name = "id_persona", nullable = false)
    private Person person;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Product product;

    public Requested() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "Requested{" +
                "id=" + id +
                ", state='" + state + '\'' +
                ", person=" + person.getId() +
                ", product=" + product.getId() +
                '}';
    }
}
