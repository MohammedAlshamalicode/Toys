package be.vdab.toys.customers;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CustomerNietGevondenException extends RuntimeException {
    public CustomerNietGevondenException( ) {
        super("Customer niet gevonden exception.");
    }
}
