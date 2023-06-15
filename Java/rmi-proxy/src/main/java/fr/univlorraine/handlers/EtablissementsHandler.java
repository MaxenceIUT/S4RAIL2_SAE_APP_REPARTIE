package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class EtablissementsHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public EtablissementsHandler(ServiceProxyImpl provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        if (Objects.equals(exchange.getRequestMethod(), "POST")) {
            InputStream requestBody = exchange.getRequestBody();
            String response = "";
            try {
                response = provider.getEtablissementsProvider().getEtablissements();
            } catch (InterruptedException e) {
                response = "An error occured while fetching restaurants";
                // Set the response status code to 500
                exchange.sendResponseHeaders(500, response.getBytes(StandardCharsets.UTF_8).length);
            }
            exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
        }
    }

}
