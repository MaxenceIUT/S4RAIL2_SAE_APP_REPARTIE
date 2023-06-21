package fr.univlorraine.utils;

import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class HttpUtils {

    /**
     * @param exchange The exchange to send the response to
     * @param response The response to send
     * @throws IOException If an error occurs while sending the response
     */
    public static void sendJSON(int statusCode, HttpExchange exchange, String response) throws IOException {
        setupCORS(exchange);
        exchange.getResponseHeaders().set("Content-Type", "application/json");

        byte[] bytes = response.getBytes(StandardCharsets.UTF_8);
        exchange.sendResponseHeaders(statusCode, bytes.length);

        OutputStream os = exchange.getResponseBody();
        os.write(bytes);
        os.close();
    }

    public static void setupCORS(HttpExchange exchange) {
        Headers headers = exchange.getResponseHeaders();
        headers.set("Access-Control-Allow-Origin", "*");
        headers.set("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
        headers.set("Access-Control-Allow-Headers", "Content-Type");
    }

}
