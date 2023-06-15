package implementation;

import service.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class RmiEtablissement implements Service {

    @Override
    public String interroge() {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://data.enseignementsup-recherche.gouv.fr//explore/dataset/fr-esr-principaux-etablissements-enseignement-superieur/download?format=json&timezone=Europe/Berlin&use_labels_for_header=false"))
                .GET()
                .build();

        HttpClient httpClient = HttpClient.newHttpClient();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            return e.getMessage();
        }

    }
}
