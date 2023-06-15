package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;
import java.sql.SQLException;

import static fr.univlorraine.utils.HttpUtils.sendJSON;

public class GoodFoodHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public GoodFoodHandler(ServiceProxyImpl provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Fetch and return all the restaurants
        String response = "";
        int statusCode = 200;
        try {
            String restaurants = provider.getGoodFoodProvider().getRestaurants();
            response = restaurants;
        } catch (SQLException | IOException e) {
            statusCode = 500;
            response = "An error occured while fetching restaurants | Error: " + e.getMessage();
        }
        sendJSON(statusCode, exchange, response);
    }

}
