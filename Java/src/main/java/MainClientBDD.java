import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class MainClientBDD {

    public static void main(String[] args) throws RemoteException, NotBoundException, SQLException {

        Registry reg = LocateRegistry.getRegistry("localhost", 1099);

        ServiceBDD serv = (ServiceBDD) reg.lookup("bdd");
        System.out.println(serv.recupererCoordonnees());

    }
}
