package fr.univlorraine;

import fr.univlorraine.utils.Destination;
import org.json.JSONArray;
import org.json.JSONObject;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void bookTable(String nomRestorant, String nom, String prenom, String nombreInvites, String numTelephone, String dateRes) throws SQLException, RemoteException {

        Connection connexion = DBConnection.getConnexion();

        PreparedStatement pst = connexion.prepareStatement("SELECT id FROM goodfood_restaurants WHERE name = ?");
        pst.setString(1, nomRestorant);

        ResultSet res = pst.executeQuery();
        int idResto = res.getInt(1);

        PreparedStatement pst2 = connexion.prepareStatement("SELECT table_id FROM goodfood_tables WHERE seat_count >= ? AND table_id NOT IN (SELECT table_id FROM goodfood_reservations WHERE reservation_date = ?)");
        pst2.setString(1, nombreInvites);
        pst2.setString(2, dateRes);

        res = pst2.executeQuery();
        int idTab = res.getInt(1);


        PreparedStatement pst3 = connexion.prepareStatement("INSERT INTO goodfood_reservations (restaurant_id, table_id, reservation_date, people_count) VALUES (?, ?, ?, ? )");
        pst3.setInt(1, idResto);
        pst3.setInt(2, idTab);
        pst3.setString(3, dateRes);
        pst3.setString(4, nombreInvites);

        pst3.executeUpdate();

    }
}
