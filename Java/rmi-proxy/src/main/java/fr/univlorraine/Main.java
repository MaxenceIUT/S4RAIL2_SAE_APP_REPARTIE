package fr.univlorraine;

import fr.univlorraine.utils.Destination;

import java.rmi.Remote;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Main {

    public static void main(String[] args) throws Exception {
        Destination destination = Destination.pickDestination(args);
        Registry registry = LocateRegistry.getRegistry(destination.getHost(), destination.getPort());

        ServiceProxyImpl serviceProxy = new ServiceProxyImpl();
        Remote exportedObject = UnicastRemoteObject.exportObject(serviceProxy, 0);

        registry.rebind("proxy", exportedObject);

        WebsiteAPI websiteAPI = new WebsiteAPI(serviceProxy);
        websiteAPI.startAPI();

        System.out.println("RMI Proxy started");
    }

}
