package client.RMIDonneeBloque;

import implementation.RmiEtablissement;
import service.Service;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class BloqueServeur {

    public static void main(String[] args) throws RemoteException, AlreadyBoundException {
        Registry reg = LocateRegistry.createRegistry(2099);
        RmiEtablissement rmiEtablissement = new RmiEtablissement();
        Service service = (Service) UnicastRemoteObject.exportObject(rmiEtablissement,0);
        reg.bind("RMIBloque",service);


    }
}
