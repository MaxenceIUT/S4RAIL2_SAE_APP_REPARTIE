package fr.univlorraine;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceEtablissements extends Remote {

    /**
     * @return La liste des principaux établissements dans l'enseignement supérieur en JSON
     * @throws RemoteException      Si une erreur réseau est survenue
     * @throws IOException          Si une erreur du client HTTP est survenue
     * @throws InterruptedException Si une erreur du client HTTP est survenue
     */
    String getEtablissements() throws RemoteException, IOException, InterruptedException;

}
