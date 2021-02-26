package go_kart_go_network;

import java.net.DatagramSocket;

public class UDPClientSocket {

    public UDPSocket udpSocket;
    public DatagramSocket socket;

    public UDPClientSocket() {
        udpSocket = new UDPSocket(false);
        socket = udpSocket.socket;
    }

    public void sendKart(Object kart) {
        udpSocket.sendObject(kart, ServerDetails.getAddress(), ServerDetails.port);
    }

    public void sendMessage(String message) {
        PacketSender.sendPacket(message, ServerDetails.getAddress(), ServerDetails.port, socket);
    }

    public String getMessage() {
        return udpSocket.getMessage(false);
    }
}
