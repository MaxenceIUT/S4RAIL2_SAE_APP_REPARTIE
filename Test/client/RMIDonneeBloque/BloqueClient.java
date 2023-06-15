package client.RMIDonneeBloque;

import service.Service;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class BloqueClient {

    public static void main(String[] args) throws RemoteException, NotBoundException {
        Registry reg = LocateRegistry.getRegistry(2099);
        Service service = (Service) reg.lookup("RMIBloque");
        System.out.println(service.interroge());
    }
}
