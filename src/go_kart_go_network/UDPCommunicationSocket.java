package go_kart_go_network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPCommunicationSocket {

    public DatagramSocket socket;
    public int port;
    public InetAddress address;

    public UDPCommunicationSocket() {
        try {
            socket = new DatagramSocket(); // bind socket to any available port

            port = socket.getLocalPort();
            address = socket.getInetAddress();

        } catch (SocketException e) {
            System.out.println("Error: cannot create communication socket");
        }
    }

    public String getMessage() {
        return PacketReceiver.receivePacket(socket);
    }

    public void respond(String message, InetAddress clientAddress, int clientPort) {
        PacketSender.sendPacket(message, clientAddress, clientPort, socket);
    }
}
