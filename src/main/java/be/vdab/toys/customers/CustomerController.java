package be.vdab.toys.customers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("{id}")
    Detail findById(@PathVariable long id) {
        return customerService.findCustomerById(id).map(customer -> new Detail(customer))
                .orElseThrow(CustomerNietGevondenException::new);
    }


    private record Detail(String name, Adres adres, String country) {
        Detail(Customer customer) {
            this(customer.getName(), customer.getAdres(), customer.getCountry().getName());
        }
    }
}
