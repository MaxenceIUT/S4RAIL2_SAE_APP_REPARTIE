package implementation;

import service.Service;
import service.ServiceCentral;

import java.util.ArrayList;

public class ServiceCentralImpl implements ServiceCentral {

    ArrayList<Service> services;
    @Override
    public void enregistrerService(Service service) {
        services.add(service);
    }




}
