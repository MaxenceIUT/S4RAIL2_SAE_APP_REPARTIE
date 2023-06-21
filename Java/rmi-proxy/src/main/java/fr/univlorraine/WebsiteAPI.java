package fr.univlorraine;

import com.sun.net.httpserver.HttpsConfigurator;
import com.sun.net.httpserver.HttpsParameters;
import com.sun.net.httpserver.HttpsServer;
import fr.univlorraine.handlers.EtablissementsHandler;
import fr.univlorraine.handlers.GoodFoodHandler;
import fr.univlorraine.handlers.ReserverTableHandler;

import javax.net.ssl.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.*;
import java.security.cert.CertificateException;

public class WebsiteAPI {

    private final GoodFoodHandler goodFoodHandler;
    private final EtablissementsHandler etablissementsHandler;

    private final ReserverTableHandler reserverTableHandler;

    public WebsiteAPI(ServiceProxyImpl serviceProxy) {
        this.goodFoodHandler = new GoodFoodHandler(serviceProxy);
        this.etablissementsHandler = new EtablissementsHandler(serviceProxy);
        this.reserverTableHandler = new ReserverTableHandler(serviceProxy);
    }

    public void startAPI() throws IOException, KeyStoreException, NoSuchAlgorithmException, CertificateException, UnrecoverableKeyException, KeyManagementException {
        // Set up the socket address
        InetSocketAddress address = new InetSocketAddress(8443);

        // Initialise the HTTPS server
        HttpsServer server = HttpsServer.create(address, 0);
        SSLContext sslContext = SSLContext.getInstance("TLS");

        // Initialise the keystore
        char[] password = "simulator".toCharArray();
        KeyStore ks = KeyStore.getInstance("JKS");
        FileInputStream fis = new FileInputStream("sae.keystore");
        ks.load(fis, password);

        // Set up the key manager factory
        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, password);

        // Set up the trust manager factory
        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(ks);

        // Set up the HTTPS context and parameters
        sslContext.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);
        server.setHttpsConfigurator(new HttpsConfigurator(sslContext) {
            public void configure(HttpsParameters params) {
                try {
                    // Initialise the SSL context
                    SSLContext c = SSLContext.getDefault();
                    SSLEngine engine = c.createSSLEngine();
                    params.setNeedClientAuth(false);
                    params.setCipherSuites(engine.getEnabledCipherSuites());
                    params.setProtocols(engine.getEnabledProtocols());

                    // Get the default parameters
                    SSLParameters defaultSSLParameters = c.getDefaultSSLParameters();
                    params.setSSLParameters(defaultSSLParameters);
                } catch (Exception ex) {
                    ex.printStackTrace();
                    System.out.println("Failed to create HTTPS port");
                }
            }
        });
        server.createContext("/api/goodfood/restaurant", this.goodFoodHandler);
        server.createContext("/api/etablissements", this.etablissementsHandler);
        server.createContext("/api/goodfood/reserver", this.reserverTableHandler);

        server.start();
    }

}
