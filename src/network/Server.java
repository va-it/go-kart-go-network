package network;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Server {
    public static int port = 2000;
    public static InetAddress address;

    public static void getAddrress() {
        try {
            address = InetAddress.getLocalHost();
        }
        catch( UnknownHostException e )
        {
            System.err.println ("Can't find host");
        }
    }
}
