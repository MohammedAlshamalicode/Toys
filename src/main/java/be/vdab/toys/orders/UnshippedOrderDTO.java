package be.vdab.toys.orders;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.time.LocalDate;
@JsonPropertyOrder({"id", "ordered", "required", "customerName", "status"})
public interface UnshippedOrderDTO {
    Long getId();
    LocalDate getOrdered();
    LocalDate getRequired();
    String getCustomerName();
    OrderStatus getStatus();
}
