package be.vdab.toys.orders;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;


public interface OrdersRepository extends JpaRepository<Order, Long> {

    @Query("""
        SELECT o.id AS id, o.ordered AS ordered, o.required AS required,
                 c.name AS customerName, o.status AS status
                FROM Order o
                JOIN o.customer c
                WHERE o.status NOT IN ('SHIPPED', 'CANCELLED')
                ORDER BY o.id
        """)
    List<UnshippedOrderDTO> findUnshippedOrders();

    Optional<Order> findOrderById(Long id);

//    @EntityGraph(attributePaths = "customer")
//    List<Order> findByStatusNotIn(List<OrderStatus> statuses, Sort sort);

    @EntityGraph(attributePaths = {"customer", "customer.country", "orderDetails", "orderDetails.product"})
    Optional<Order> findById(long id);

}

