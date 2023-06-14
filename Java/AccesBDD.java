import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;

public class AccesBDD implements ServiceBDD {
    @Override
    public void recupererCoordonnees() {


    }

    @Override
    public void reserverTable(String nom, String prenom, String nb, String tel) {

    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, RemoteException {

//        AccesBDD acc = new AccesBDD();
//        ServiceBDD sb = (ServiceBDD) UnicastRemoteObject.exportObject(acc, 0);
//
//        Registry reg = null;

        // chargement driver
        System.out.println("Driver loaded");

        // etablir connection
        Connection connection = DriverManager.getConnection("jdbc:postgresql://db.xsmbkjnrbdkmorujjhtk.supabase.co:5432/postgres","postgres", "JBRVB8YpDClwzNF7@#jAGQY#K@b2mh^G&46");
        System.out.println("Data connected");

        Statement st = connection.createStatement();

        ResultSet res = st.executeQuery("SELECT * FROM Restaurant;");

        System.out.println("Resultat :");
        while (res.next())
            System.out.println(res.getString(1) + "\t" +
                    res.getString(2) + "\t" + res.getString(3));
        connection.close();



    }
}
