package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceEtablissements;
import fr.univlorraine.WebsiteAPI;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class EtablissementsHandler implements HttpHandler {

    private final ServiceEtablissements provider;

    public EtablissementsHandler(ServiceEtablissements provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        // Reserve a table
        WebsiteAPI w = null;
        if (Objects.equals(exchange.getRequestMethod(), "POST")) {
            InputStream requestBody = exchange.getRequestBody();
            String requestBodyString = new String(requestBody.readAllBytes(), StandardCharsets.UTF_8);
            exchange.getResponseBody().write(requestBodyString.getBytes(StandardCharsets.UTF_8));
        }
    }

}
