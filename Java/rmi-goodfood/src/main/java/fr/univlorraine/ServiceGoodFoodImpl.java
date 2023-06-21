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

        try {
            Connection connexion = DBConnection.getConnexion();

            System.out.println("Recuperation de l'id resto");
            PreparedStatement pst = connexion.prepareStatement("SELECT id FROM goodfood_restaurants WHERE name = ?");
            pst.setString(1, nomRestorant);

            ResultSet res = pst.executeQuery();
            int idResto = -1;
            if (res.next()) {
                idResto = res.getInt("id");
                System.out.println("id Resto : " + idResto);
            }
            res.close();
            pst.close();

            System.out.println("recuperation d 'une table dispo");
            PreparedStatement pst2 = connexion.prepareStatement("SELECT table_id FROM goodfood_tables WHERE seat_count >= ? AND table_id NOT IN (SELECT table_id FROM goodfood_reservations WHERE EXTRACT(HOUR FROM reservation_date) = EXTRACT(HOUR FROM ?::timestamp))");
            System.out.println(nombreInvites);
            pst2.setInt(1, Integer.parseInt(nombreInvites));
            pst2.setTimestamp(2, Timestamp.valueOf(dateRes));
            int idTab = -1;
            try {
                ResultSet res2 = pst2.executeQuery();
                System.out.println("requete executé");

                if (res2.next()) {
                    idTab = res2.getInt("table_id");
                    System.out.println("table dispo: " + idTab);
                } else {
                    System.out.println("pas de resultat trouvé");
                }

                res2.close();
                pst2.close();

            } catch (Exception e) {
                System.out.println("erreur requete table");
            }


            System.out.println("insertion dans la bdd");
            PreparedStatement pst3 = connexion.prepareStatement("INSERT INTO goodfood_reservations (restaurant_id, table_id, reservation_date, people_count, first_name, last_name, phone_number) VALUES (?, ?, ?, ?, ?, ?, ? )");
            pst3.setInt(1, idResto);
            pst3.setInt(2, idTab);
            pst3.setTimestamp(3, Timestamp.valueOf(dateRes));
            System.out.println(nombreInvites);
            pst3.setInt(4, Integer.parseInt(nombreInvites));
            pst3.setString(5, prenom);
            pst3.setString(6, nom);
            pst3.setString(7, numTelephone);


            pst3.executeUpdate();


            System.out.println("inseré");

            res.close();
            pst3.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
