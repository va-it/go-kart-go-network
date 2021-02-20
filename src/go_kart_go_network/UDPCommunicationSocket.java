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
            packetReceiver = new PacketReceiver();
        } catch (SocketException e) {
            System.out.println("Error: cannot create communication socket");
        }
    }

    public String getMessage() {
        return packetReceiver.receivePacket(socket);
    }

    public void sendMessage(String message) {
        PacketSender.sendPacket(message, packetReceiver.senderInetAddress, packetReceiver.senderPort, socket);
    }

    public void sendObject(Object object, InetAddress recipientAddress, int recipientPort) {
        PacketSender.sendPacketWithObject(object, recipientAddress, recipientPort, socket);
    }

    public Object getObject() {
        return packetReceiver.receiveObject(socket);
    }
}
