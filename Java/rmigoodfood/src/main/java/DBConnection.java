import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String userName = "postgres";

    private static final String password = "JBRVB8YpDClwzNF7";

    static String dbName = "postgres";

    private static String urlDB = "jdbc:postgresql://db.xsmbkjnrbdkmorujjhtk.supabase.co:";

    private static Connection instanceConnexion;


    public static Connection getConnexion() throws SQLException {
        if (DBConnection.instanceConnexion == null) {
            Properties connectionProps = new Properties();
            connectionProps.put("user", DBConnection.userName);
            connectionProps.put("password", DBConnection.password);
            String portNumber = "5432";
            urlDB += portNumber + "/" + dbName;
            DBConnection.instanceConnexion = DriverManager.getConnection(urlDB, connectionProps);
        }
        return DBConnection.instanceConnexion;
    }

    public static void setNomDB(String DB) throws SQLException {
        dbName = DB;
        instanceConnexion = getConnexion();
    }

    public static String getDbName() {
        return dbName;
    }
}
