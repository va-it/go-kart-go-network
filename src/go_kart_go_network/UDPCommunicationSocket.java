package go_kart_go_network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPCommunicationSocket {

    public DatagramSocket socket;
    public int port;
    public InetAddress address;
    public PacketReceiver packetReceiver;

    public UDPCommunicationSocket(boolean server) {
        try {
            if (server) {
                socket = new DatagramSocket(ServerDetails.port);
                port = ServerDetails.port;
                address = ServerDetails.getAddress();
            } else {
                socket = new DatagramSocket(); // bind socket to any available port
                port = socket.getLocalPort();
                address = socket.getInetAddress();
            }
            // this in theory allows to store the sender port and address
            packetReceiver = new PacketReceiver();
        } catch (SocketException e) {
            System.out.println("Error: cannot create communication socket");
        }
    }

    // used by both client and server
    public String getMessage() {
        return packetReceiver.receivePacket(socket);
    }

    // used by server
    public void sendMessage(String message) {
        PacketSender.sendPacket(message, packetReceiver.senderInetAddress, packetReceiver.senderPort, socket);
    }

    // used by client
    public void sendObject(Object object, InetAddress recipientAddress, int recipientPort) {
        PacketSender.sendPacketWithObject(object, recipientAddress, recipientPort, socket);
    }

    // used by server
    public Object getObject() {
        return packetReceiver.receiveObject(socket);
    }
}
