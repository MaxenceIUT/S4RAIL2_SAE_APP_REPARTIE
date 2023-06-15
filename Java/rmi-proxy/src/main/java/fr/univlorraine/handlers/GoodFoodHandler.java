package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;

public class GoodFoodHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public GoodFoodHandler(ServiceProxyImpl provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Fetch and return all the restaurants
        try {
            exchange.getResponseBody().write(provider.getRestaurants().getBytes(StandardCharsets.UTF_8));
        } catch (SQLException | IOException e) {
            exchange.getResponseBody().write("An error occured while fetching restaurants".getBytes(StandardCharsets.UTF_8));
            throw new RuntimeException(e);
        }
    }

}
