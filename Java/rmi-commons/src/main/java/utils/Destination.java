package utils;

public class Destination {

    private final String host;
    private final int port;

    public Destination(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    /**
     * Parse the command line arguments to get the destination
     *
     * @param args Arguments from the command line
     * @return Destination object parsed
     * @throws Exception If the port is not an integer
     */
    public static Destination pickDestination(String[] args) throws Exception {
        String host = "localhost";
        int port = 1099;

        // On récupère les informations de connexion depuis la ligne de commande
        if (args.length == 1) {
            host = args[0];
        } else if (args.length == 2) {
            host = args[0];
            try {
                port = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new Exception("Le port doit être un entier", e);
            }
        }
        return new Destination(host, port);
    }


}
