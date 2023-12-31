package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;

import static fr.univlorraine.utils.HttpUtils.sendJSON;

public class GoodFoodHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public GoodFoodHandler(ServiceProxyImpl provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) {
        Thread fetcher = new Thread(() -> {
            int statusCode = 200;
            String response;
            try {
                if (provider.getGoodFoodProvider() == null) throw new Exception("ServiceEtablissements unavailable");
                response = provider.getGoodFoodProvider().getRestaurants();
            } catch (Exception e) {
                statusCode = 500;
                response = "An error occured while fetching restaurants | Error: " + e.getMessage();
            }
            try {
                sendJSON(statusCode, exchange, response);
            } catch (IOException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        });
        fetcher.start();
    }

}
