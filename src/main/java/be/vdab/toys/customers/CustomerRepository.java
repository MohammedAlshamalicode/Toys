package be.vdab.toys.customers;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.EntityGraph;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
//
//    @EntityGraph(attributePaths = {"customer"})
//    Optional<Customer> findById(Long id);

}
