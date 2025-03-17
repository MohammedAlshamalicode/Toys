package be.vdab.toys.customers;

import be.vdab.toys.countries.Country;
import be.vdab.toys.orders.Order;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id ;

    private String name;

    @Embedded
    private Adres adres ;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "countryId")
    private Country country;

    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new LinkedHashSet<>();

    @Version
    private int version ;

    public Customer( String name, Adres adres, Country country) {
        this.name = name;
        this.adres = adres;
        this.country = country;
    }

    protected Customer() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Adres getAdres() {
        return adres;
    }

    public int getVersion() {
        return version;
    }

    public Country getCountry() {
        return country;
    }

    public Set<Order> getOrders() {
        return orders;
    }
}
