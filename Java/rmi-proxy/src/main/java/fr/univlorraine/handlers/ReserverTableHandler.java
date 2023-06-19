package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.rmi.RemoteException;
import java.sql.SQLException;
import java.util.Scanner;

public class ReserverTableHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public ReserverTableHandler (ServiceProxyImpl provider) {
        this.provider = provider;
    }
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestBody = new String(exchange.getRequestBody().readAllBytes());

        String[] params = requestBody.split("&");

        for (String s : params) {
            System.out.println(s);
        }

        String nom = null, prenom = null, nbInv = null, tel = null, nomResto = null;

        if (params.length == 5) {
            nom = params[0];
            prenom = params[1];
            nbInv = params[2];
            tel = params[3];
            nomResto = params[4];
        } else {
            System.out.println("nb argument incorrect");
        }


        try {
            provider.getGoodFoodProvider().bookTable(nomResto, nom, prenom, nbInv, tel);
        } catch (Exception e) {
            String response = "\"An error occured for book a table\";";
            exchange.sendResponseHeaders(500, response.getBytes(StandardCharsets.UTF_8).length);
            exchange.getResponseBody().write(response.getBytes(StandardCharsets.UTF_8));
        }
    }
}
