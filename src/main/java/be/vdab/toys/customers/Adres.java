package be.vdab.toys.customers;

import jakarta.persistence.Embeddable;

@Embeddable
public record Adres (String streetAndNumber, String city, String state, String postalCode) { }
