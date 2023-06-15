import java.rmi.Remote;

public interface ServiceProxy extends Remote {

    void registerGoodFoodProvider(ServiceGoodFood provider);

    void registerEtablissementsProvider(ServiceEtablissements provider);

}
