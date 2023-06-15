package fr.univlorraine;

public class ServiceProxyImpl implements ServiceProxy {

    private ServiceGoodFood goodFoodProvider;
    private ServiceEtablissements etablissementsProvider;

    @Override
    public void registerGoodFoodProvider(ServiceGoodFood provider) {
        this.goodFoodProvider = provider;
    }

    @Override
    public void registerEtablissementsProvider(ServiceEtablissements provider) {
        this.etablissementsProvider = provider;
    }

    public ServiceGoodFood getGoodFoodProvider() {
        return goodFoodProvider;
    }

    public ServiceEtablissements getEtablissementsProvider() {
        return etablissementsProvider;
    }

}
