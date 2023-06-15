import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServiceBDD extends Remote {

    String recupererCoordonnees() throws SQLException, RemoteException;

    void reserverTable(String nom, String prenom, String nb, String tel) throws RemoteException;
}
