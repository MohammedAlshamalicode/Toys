package be.vdab.toys.orders;

import be.vdab.toys.customers.Customer;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private LocalDate ordered ;
    private LocalDate required ;
    private LocalDate shipped ;

    private String comments ;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId", nullable = false)
    private Customer customer;

    @Version
    private int version;

    @OneToMany(mappedBy = "order")
    private Set<OrderDetails> orderDetails = new LinkedHashSet<>();


    protected Order() {}

    public Order( LocalDate ordered, LocalDate required, LocalDate shipped,
                 String comments , OrderStatus status, Customer customer, Set<OrderDetails> orderDetails) {
        this.ordered = ordered;
        this.required = required;
        this.shipped = shipped;
        this.comments = comments;
        this.status = status;
        this.customer = customer;
        this.orderDetails = orderDetails;
    }

    public long getId() {return id;}

    public LocalDate getOrdered() {return ordered;}

    public LocalDate getRequired() {return required;}

    public LocalDate getShipped() {return shipped;}

    public String getComments() {return comments;}

    public OrderStatus getStatus() {return status;}

    public Customer getCustomer() {return customer;}

    public Set<OrderDetails> getOrderDetails() {return orderDetails;}

    public void setStatus(OrderStatus status) {this.status = status;}

    public void setShipped(LocalDate shipped) {this.shipped = shipped;}
}
