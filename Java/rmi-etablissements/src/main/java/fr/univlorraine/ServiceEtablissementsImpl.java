package fr.univlorraine;

import fr.univlorraine.utils.Destination;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServiceEtablissementsImpl implements ServiceEtablissements {

    public static void main(String[] args) throws Exception {
        Destination destination = Destination.pickDestination(args);
        Registry reg = LocateRegistry.getRegistry(destination.getHost(), destination.getPort());

        ServiceEtablissementsImpl acc = new ServiceEtablissementsImpl();
        ServiceEtablissements sb = (ServiceEtablissements) UnicastRemoteObject.exportObject(acc, 0);

        ServiceProxy proxy = (ServiceProxy) reg.lookup("proxy");
        proxy.registerEtablissementsProvider(sb);

        System.out.println("Service Etablissements lancé");
    }

    @Override
    public String getEtablissements() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://data.enseignementsup-recherche.gouv.fr//explore/dataset/fr-esr-principaux-etablissements-enseignement-superieur/download?format=json&timezone=Europe/Berlin&use_labels_for_header=false"))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
