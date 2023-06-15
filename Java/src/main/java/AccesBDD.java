import org.json.*;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class AccesBDD implements ServiceBDD {
    @Override
    public String recupererCoordonnees() throws SQLException {

        Connection connection = DBConnection.getConnexion();

        Statement st = connection.createStatement();
        ResultSet res = st.executeQuery("SELECT name, latitude,longitude FROM goodfood_restaurants;");

        JSONArray json = new JSONArray();
        JSONObject json2;

        while (res.next()) {
            json2 = new JSONObject();
            json2.put("nom", res.getString(1));
            json2.put("latitude", res.getString(2));
            json2.put("longitude", res.getString(3));
            json.put(json2);
        }

        return json.toString();
    }

    @Override
    public void reserverTable(String nom, String prenom, String nb, String tel) {
    throw new Error();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, RemoteException {

        AccesBDD acc = new AccesBDD();
        ServiceBDD sb = (ServiceBDD) UnicastRemoteObject.exportObject(acc, 0);

        Registry reg = LocateRegistry.createRegistry(1099);
        reg.rebind("bdd", sb);

        System.out.println("Service BDD lance");


    }
}
