package go_kart_go_network;

import java.net.DatagramSocket;
import java.net.SocketException;

public class CommunicationSocket {

    public DatagramSocket socket;

    public CommunicationSocket() {
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            System.out.println("Error: cannot create communication socket");
        }
    }
}