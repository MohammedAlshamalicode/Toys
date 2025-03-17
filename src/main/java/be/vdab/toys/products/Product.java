package be.vdab.toys.products;

import be.vdab.toys.customers.Customer;
import be.vdab.toys.orders.OrderDetails;
import be.vdab.toys.productLines.ProductLine;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.Set;

@Entity
@Table(name = "products")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name ;

    private String scale;

    private String description;

    private int inStock ;

    private int inOrder ;

    private BigDecimal price;

    @Version
    private int version;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productlineId")
    private ProductLine productLine;

    @OneToMany(mappedBy = "product")
    private Set<OrderDetails> orderDetails;

    protected Product() {}

    public Product(String name, String scale, String description, int inStock,
                   int inOrder, BigDecimal price,ProductLine productLine ,int version) {
        this.name = name;
        this.scale = scale;
        this.description = description;
        this.inStock = inStock;
        this.inOrder = inOrder;
        this.price = price;
        this.version = version;
        this.productLine = productLine;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getScale() {
        return scale;
    }

    public String getDescription() {
        return description;
    }

    public int getInStock() {
        return inStock;
    }

    public int getInOrder() {
        return inOrder;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getVersion() {
        return version;
    }

    public ProductLine getProductLine() {
        return productLine;
    }

    public Set<OrderDetails> getOrderDetails() {
        return orderDetails;
    }

    public void setInStock(int inStock) {
        this.inStock = inStock;
    }

    public void setInOrder(int inOrder) {
        this.inOrder = inOrder;
    }

}
