package fr.univlorraine.handlers;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import fr.univlorraine.ServiceProxyImpl;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import static fr.univlorraine.utils.HttpUtils.sendJSON;

public class ReserverTableHandler implements HttpHandler {

    private final ServiceProxyImpl provider;

    public ReserverTableHandler(ServiceProxyImpl provider) {
        this.provider = provider;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        int statusCode = 200;

        String requestBody = new String(exchange.getRequestBody().readAllBytes());
        String decodedRequestBody = URLDecoder.decode(requestBody, StandardCharsets.UTF_8);

        String[] params = decodedRequestBody.split("&");

        String nom = null, prenom = null, nbInv = null, tel = null, nomResto = null, dateRes = null;

        if (params.length == 6) {
            nom = params[0].split("=")[1];
            prenom = params[1].split("=")[1];
            nbInv = params[2].split("=")[1];
            System.out.println(nbInv);
            tel = params[3].split("=")[1];
            nomResto = params[4].split("=")[1];
            dateRes = params[5].replace('T', ' ').split("=")[1] + ":00";
        } else {
            System.out.println("nb argument incorrect");
        }

        try {
            provider.getGoodFoodProvider().bookTable(nomResto, nom, prenom, nbInv, tel, dateRes);
            System.out.println("Réservation effectuée");
            response = "Done!";
        } catch (Exception e) {
            response = "\"An error occured while booking a table\";";
            statusCode = 500;
        }

        sendJSON(statusCode, exchange, response);
    }

}
