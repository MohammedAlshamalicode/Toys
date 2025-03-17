package be.vdab.toys.orders;

import be.vdab.toys.customers.Adres;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<OrderDetailDTO> findAll() {
        return orderService.findAll()
                .stream()
                .map(OrderDetailDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("unshipped")
    public List<UnshippedOrderDTO> findUnshipped() {
        return orderService.findUnshippedOrders();
    }

    @GetMapping("{id}")
     OrderDetailDTO findById(@PathVariable Long id) {
        return orderService.findById(id)
                .map(OrderDetailDTO::new)
                .orElseThrow(OrderNotFoundException::new);
    }

    @PostMapping("{id}/shippings")
    public void shipOrder(@PathVariable Long id) {
        try {
            orderService.shipOrder(id);
        } catch (OrderNotFoundException | OrderAlreadyShippedException | OnvoldoendeVoorraadException e) {
            throw new OrderAlreadyShippedException();
        }
    }

    private record OrderDetailDTO(Long id, String ordered, String required, String customerName,
                                  String countryName, BigDecimal value, List<OrderDetailItemDTO> details) {

        OrderDetailDTO(Order order) {
            this(order.getId(),
                    order.getOrdered().toString(),
                    order.getRequired().toString(),
                    order.getCustomer().getName(),
                    order.getCustomer().getCountry().getName(),
                    order.getOrderDetails().stream()
                            .map(detail -> detail.getPriceEach().multiply(BigDecimal.valueOf(detail.getOrdered())))
                            .reduce(BigDecimal.ZERO, BigDecimal::add), // حساب إجمالي القيمة
                    order.getOrderDetails().stream()
                            .map(OrderDetailItemDTO::new)
                            .toList());
        }
    }

    private record OrderDetailItemDTO(int ordered, BigDecimal priceEach, BigDecimal value, String productName) {
        OrderDetailItemDTO(OrderDetails orderDetail) {
            this(orderDetail.getOrdered(),
                    orderDetail.getPriceEach(),
                    orderDetail.getPriceEach().multiply(BigDecimal.valueOf(orderDetail.getOrdered())), // Bereken de waarde van elk product
                    orderDetail.getProduct().getName());
        }
    }
}
