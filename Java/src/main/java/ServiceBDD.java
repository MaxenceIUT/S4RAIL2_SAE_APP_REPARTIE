import org.json.JSONObject;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.sql.SQLException;

public interface ServiceBDD extends Remote {

    public String recupererCoordonnees() throws SQLException, RemoteException;

    public void reserverTable(String nom, String prenom, String nb, String tel) throws RemoteException;
}
