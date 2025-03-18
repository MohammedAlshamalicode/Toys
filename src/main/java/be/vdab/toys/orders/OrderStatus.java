package be.vdab.toys.orders;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;


public enum OrderStatus {
    PROCESSING, SHIPPED, CANCELLED, WAITING, RESOLVED, DISPUTED ;
}
