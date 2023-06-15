package fr.univlorraine;

import java.rmi.RemoteException;

public class ServiceProxyImpl implements ServiceProxy {

    private ServiceGoodFood goodFoodProvider;
    private ServiceEtablissements etablissementsProvider;

    @Override
    public void registerGoodFoodProvider(ServiceGoodFood provider) throws RemoteException {
        this.goodFoodProvider = provider;
    }

    @Override
    public void registerEtablissementsProvider(ServiceEtablissements provider) throws RemoteException {
        this.etablissementsProvider = provider;
    }

    public ServiceGoodFood getGoodFoodProvider() {
        return goodFoodProvider;
    }

    public ServiceEtablissements getEtablissementsProvider() {
        return etablissementsProvider;
    }

}
