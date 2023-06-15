package proxy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;
import implementation.RmiEtablissement;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

public class ServerProxy {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/test", new MyHandler());

        server.start();
        System.out.println("Server started");

    }

    static class MyHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange exchange) throws IOException {
//            String jsonResponse = "{\"message\": \"Hello, World!\"}"; //

            RmiEtablissement rmiEtablissement = new RmiEtablissement();

            String jsonResponse = rmiEtablissement.interroge();



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