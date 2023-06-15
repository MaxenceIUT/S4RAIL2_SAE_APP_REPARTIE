import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Objects;

public class Main {

    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create();
        server.bind(new InetSocketAddress(80), 0);
        server.createContext("/api/goodfood/restaurant", exchange -> {
            // Fetch and return all the restaurants
        });
        server.createContext("/api/goodfood/table/reserve", exchange -> {
            // Reserve a table
            if(Objects.equals(exchange.getRequestMethod(), "POST")){

            }
        });
        server.start();
    }

}
