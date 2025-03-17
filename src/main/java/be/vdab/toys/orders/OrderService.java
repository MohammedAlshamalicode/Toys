package be.vdab.toys.orders;

import be.vdab.toys.products.Product;
import be.vdab.toys.products.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class OrderService {

    private final OrdersRepository ordersRepository;
    private final ProductRepository productRepository;

    public OrderService(OrdersRepository ordersRepository, ProductRepository productRepository) {
        this.ordersRepository = ordersRepository;
        this.productRepository = productRepository;
    }

    public List<Order> findAll() {
        return ordersRepository.findAll();
    }

    public List<UnshippedOrderDTO> findUnshippedOrders() {
        return ordersRepository.findUnshippedOrders();
    }

    public Optional<Order> findById(Long id) {
        return ordersRepository.findOrderById(id);
    }

    @Transactional(readOnly = false)
    public void shipOrder(Long id) {
        Order order = ordersRepository.findOrderById(id)
                .orElseThrow(OrderNotFoundException::new);

        if (order.getStatus() == OrderStatus.SHIPPED) {
            throw new OrderAlreadyShippedException();
        }

        for (OrderDetails detail : order.getOrderDetails()) {
            Product product = detail.getProduct();
            if (product.getInStock() < detail.getOrdered()) {
                throw new OnvoldoendeVoorraadException();
            }
        }

        // Orderstatus bijwerken naar  SHIPPED
        order.setStatus(OrderStatus.SHIPPED);
        order.setShipped(LocalDate.now());

        productRepository.updateStock(id);

        // Wijzigingen opslaan
        ordersRepository.save(order);
    }
}
