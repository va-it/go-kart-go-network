package go_kart_go_network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class ServerDetails {
    public static int port = 2000;
    public static String hostName = "localhost";
    private static InetAddress address;

    public static InetAddress getAddress() {
        try {
            address = InetAddress.getByName(hostName);
            return address;
        }
        catch( UnknownHostException e )
        {
            System.err.println ("Can't find host");
        }
        return address;
    }
}
