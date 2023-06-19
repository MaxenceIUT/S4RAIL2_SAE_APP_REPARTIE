package fr.univlorraine;

import com.sun.net.httpserver.HttpServer;
import fr.univlorraine.handlers.EtablissementsHandler;
import fr.univlorraine.handlers.GoodFoodHandler;
import fr.univlorraine.handlers.ReserverTableHandler;

import java.io.IOException;
import java.net.InetSocketAddress;

public class WebsiteAPI {

    private final GoodFoodHandler goodFoodHandler;
    private final EtablissementsHandler etablissementsHandler;

    private final ReserverTableHandler reserverTableHandler;

    public WebsiteAPI(ServiceProxyImpl serviceProxy) {
        this.goodFoodHandler = new GoodFoodHandler(serviceProxy);
        this.etablissementsHandler = new EtablissementsHandler(serviceProxy);
        this.reserverTableHandler = new ReserverTableHandler(serviceProxy);
    }

    public void startAPI() throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);

        server.createContext("/api/goodfood/restaurant", this.goodFoodHandler);
        server.createContext("/api/etablissements", this.etablissementsHandler);
        server.createContext("/api/goodfood/reserver", this.reserverTableHandler);

        server.start();
    }

}
