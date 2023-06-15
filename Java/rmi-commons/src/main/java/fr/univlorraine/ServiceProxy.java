package fr.univlorraine;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ServiceProxy extends Remote {

    void registerGoodFoodProvider(ServiceGoodFood provider) throws RemoteException;

    void registerEtablissementsProvider(ServiceEtablissements provider) throws RemoteException;

}
