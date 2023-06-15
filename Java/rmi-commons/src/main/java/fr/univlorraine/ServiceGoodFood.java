package fr.univlorraine;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServiceGoodFood extends Remote {

    /**
     * @return Un JSON des restaurants de Nancy avec leur nom, latitude et longitude
     * @throws SQLException    Si une erreur SQL est survenue
     * @throws RemoteException Si une erreur réseau est survenue
     */
    String getRestaurants() throws SQLException, RemoteException;

    /**
     * @param nom           Le nom du client
     * @param prenom        Le prénom du client
     * @param nombreInvites Le nombre d'invités
     * @param numTelephone  Le numéro de téléphone du client
     * @throws RemoteException Si une erreur réseau est survenue
     * @throws SQLException    Si une erreur SQL est survenue
     */
    void bookTable(String nom, String prenom, String nombreInvites, String numTelephone) throws SQLException, RemoteException;
}
