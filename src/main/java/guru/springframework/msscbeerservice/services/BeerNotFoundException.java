package guru.springframework.msscbeerservice.services;

import java.util.UUID;

public class BeerNotFoundException extends RuntimeException {
    public BeerNotFoundException(UUID uuid) {
        super("Cannot find beer with id: " + uuid);
    }
}
