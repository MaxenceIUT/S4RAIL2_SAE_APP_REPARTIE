package fr.univlorraine;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "JBRVB8YpDClwzNF7";
    private static final String databaseName = "postgres";
    private static final String jdbcBaseString = "jdbc:postgresql://db.xsmbkjnrbdkmorujjhtk.supabase.co:";
    private static Connection instanceConnexion;

    public static Connection getConnexion() throws SQLException {
        if (instanceConnexion == null) {
            String jdbcString = jdbcBaseString;
            Properties connectionProps = new Properties();
            connectionProps.put("user", USERNAME);
            connectionProps.put("password", PASSWORD);
            String portNumber = "5432";
            jdbcString += portNumber + "/" + databaseName;
            instanceConnexion = DriverManager.getConnection(jdbcString, connectionProps);
        }
        return instanceConnexion;
    }

}
