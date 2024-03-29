package go_kart_go_network;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPSocket {

    public DatagramSocket socket;
    public int port;
    public InetAddress address;
    public PacketReceiver packetReceiver;

    public UDPSocket(boolean server) {
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

    public String getMessage(boolean server) {
        return packetReceiver.receivePacket(socket, server);
    }

    public void sendMessage(String message, InetAddress recipientAddress, int recipientPort) {
        PacketSender.sendPacket(message, recipientAddress, recipientPort, socket);
    }

    public void sendObject(Object object, InetAddress recipientAddress, int recipientPort) {
        PacketSender.sendPacketWithObject(object, recipientAddress, recipientPort, socket);
    }

    public Object getObject() {
        return packetReceiver.receiveObject(socket);
    }

    public void closeSocket() {
        this.socket.close();
    }

    public boolean socketIsClosed() {
        return this.socket.isClosed();
    }
}
