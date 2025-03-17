package be.vdab.toys.orders;

import java.time.LocalDate;

public interface UnshippedOrderDTO {
    Long getId();
    LocalDate getOrdered();
    LocalDate getRequired();
    String getCustomerName();
    OrderStatus getStatus();
}
