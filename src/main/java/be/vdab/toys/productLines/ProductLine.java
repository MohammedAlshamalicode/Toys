package be.vdab.toys.productLines;

import be.vdab.toys.orders.Order;
import be.vdab.toys.products.Product;
import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table(name = "productlines")
public class ProductLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name ;

    private String description ;

    @OneToMany(mappedBy = "productLine")
    private Set<Product> products;

    @Version
    private int version ;

    protected ProductLine() {}

    public ProductLine(long id, String name, String description, int version) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.version = version;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getVersion() {
        return version;
    }

    public Set<Product> getProducts() {
        return products;
    }
}
