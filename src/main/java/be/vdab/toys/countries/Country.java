package be.vdab.toys.countries;

import be.vdab.toys.customers.Customer;
import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;

    private  String name;

    @OneToMany(mappedBy = "country") @OrderBy("name")
    private Set<Customer> customers = new LinkedHashSet<>();

    public Country( String name) {
        this.name = name;
    }

    protected Country() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<Customer> getCustomers() {
        return customers;
    }
}
