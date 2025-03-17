package be.vdab.toys.orders;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderAlreadyShippedException extends RuntimeException {
    public OrderAlreadyShippedException() {
        super("Order already shipped.");
    }
}
