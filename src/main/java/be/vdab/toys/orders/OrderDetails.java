package be.vdab.toys.orders;

import be.vdab.toys.productLines.ProductLine;
import be.vdab.toys.products.Product;
import jakarta.persistence.*;

import java.math.BigDecimal;

@Entity
@Table(name = "orderdetails")
public class OrderDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private int ordered ;

    private BigDecimal priceEach ;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "productId")
    private Product product;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "orderId")
    private Order order;



    protected OrderDetails() {}

    public OrderDetails(Product product, Order order, int ordered, BigDecimal priceEach) {
        this.product = product;
        this.order = order;
        this.ordered = ordered;
        this.priceEach = priceEach;
    }

    public long getId() {
        return id;
    }

    public int getOrdered() {
        return ordered;
    }

    public BigDecimal getPriceEach() {
        return priceEach;
    }

    public Product getProduct() {
        return product;
    }

    public Order getOrder() {
        return order;
    }
}
