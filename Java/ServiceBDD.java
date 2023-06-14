import java.rmi.Remote;

public interface ServiceBDD extends Remote {

    public void recupererCoordonnees();

    public void reserverTable(String nom, String prenom, String nb, String tel);
}
