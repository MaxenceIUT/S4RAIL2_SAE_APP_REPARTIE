package fr.univlorraine;

import fr.univlorraine.utils.Destination;
import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ServiceGoodFoodImpl implements ServiceGoodFood {

    public static void main(String[] args) throws Exception {
        Destination destination = Destination.pickDestination(args);
        Registry reg = LocateRegistry.getRegistry(destination.getHost(), destination.getPort());

        ServiceGoodFoodImpl acc = new ServiceGoodFoodImpl();
        ServiceGoodFood sb = (ServiceGoodFood) UnicastRemoteObject.exportObject(acc, 0);

        ServiceProxy proxy = (ServiceProxy) reg.lookup("proxy");
        proxy.registerGoodFoodProvider(sb);

        System.out.println("Service GoodFood lancé");
    }

    @Override
    public String getRestaurants() throws SQLException {
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
    public void bookTable(String nom, String prenom, String nombreInvites, String numTelephone) throws SQLException, RemoteException {
        throw new Error("TODO: Not implemented");
    }
}
