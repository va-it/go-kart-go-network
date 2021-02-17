package go_kart_go_network;

import java.net.InetAddress;

public class Server {
    public static int port;
    public static InetAddress address;

    public static InetAddress getAddrress() {
        return Server.address;
    }

    public static void setAddress(InetAddress address) {
        Server.address = address;
    }

    public static int getPort() {
        return port;
    }

    public static void setPort(int port) {
        Server.port = port;
    }
}
