import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        // register rmi proxy interfaces ohlalala trop bien

        WebsiteAPI websiteAPI = new WebsiteAPI(serviceProxy);
        websiteAPI.startAPI();

        System.out.println("RMI Proxy started");
    }

}
