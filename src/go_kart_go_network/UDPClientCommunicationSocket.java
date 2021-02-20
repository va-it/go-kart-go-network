package go_kart_go_network;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientCommunicationSocket {

    public UDPCommunicationSocket udpCommunicationSocket;
    public DatagramSocket socket;

    public UDPClientCommunicationSocket() {
        udpCommunicationSocket = new UDPCommunicationSocket(false);
        socket = udpCommunicationSocket.socket;
    }

    public void sendKart(Object kart) {
        udpCommunicationSocket.sendObject(kart, ServerDetails.getAddress(), ServerDetails.port);
    }

    public void sendMessage(String message) {
        PacketSender.sendPacket(message, ServerDetails.getAddress(), ServerDetails.port, socket);
    }

    public String getMessage() {
        return udpCommunicationSocket.getMessage();
    }
}
