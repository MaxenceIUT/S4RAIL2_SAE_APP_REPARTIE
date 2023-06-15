package fr.univlorraine;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import fr.univlorraine.handlers.EtablissementsHandler;
import fr.univlorraine.handlers.GoodFoodHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class WebsiteAPI {

    private GoodFoodHandler goodFoodHandler;
    private EtablissementsHandler etablissementsHandler;

    public WebsiteAPI(ServiceProxyImpl serviceProxy) {
        this.goodFoodHandler = new GoodFoodHandler(serviceProxy.getGoodFoodProvider());
        this.etablissementsHandler = new EtablissementsHandler(serviceProxy.getEtablissementsProvider());
    }

    public void startAPI() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/goodfood/restaurant", this.goodFoodHandler);
        server.createContext("/api/goodfood/table/reserve", this.etablissementsHandler);

        server.start();
    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String jsonResponse = "{\"message\": \"Hello, World!\"}"; //

            exchange.getResponseHeaders().set("Access-Control-Allow-Origin", "*");
            exchange.getResponseHeaders().set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
            exchange.getResponseHeaders().set("Access-Control-Allow-Headers", "Origin, Content-Type, Accept");

            byte[] responseBytes = jsonResponse.getBytes();
            exchange.sendResponseHeaders(200, responseBytes.length);
            exchange.getResponseHeaders().set("Content-Type", "application/json");


            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write(responseBytes);
            outputStream.close();
        }
    }

}
