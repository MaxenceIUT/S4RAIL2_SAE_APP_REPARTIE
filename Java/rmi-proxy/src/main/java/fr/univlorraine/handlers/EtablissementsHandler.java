package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

import static fr.univlorraine.utils.HttpUtils.sendJSON;

public class EtablissementsHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public EtablissementsHandler(ServiceProxyImpl provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
            int statusCode = 200;
            String response = "";
            try {
                response = provider.getEtablissementsProvider().getEtablissements();
            } catch (InterruptedException e) {
                statusCode = 500;
                response = "An error occured while fetching restaurants | Error: " + e.getMessage();
            }
            sendJSON(statusCode, exchange, response);


    }

}
