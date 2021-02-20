package go_kart_go_network;

import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPClientCommunicationSocket {

    public UDPCommunicationSocket udpCommunicationSocket;
    public DatagramSocket socket;

    public UDPClientCommunicationSocket() {
        udpCommunicationSocket = new UDPCommunicationSocket();
        socket = udpCommunicationSocket.socket;
    }

    public void sendKart(Object kart, InetAddress recipientAddress, int recipientPort) {
        udpCommunicationSocket.sendObject(kart, recipientAddress, recipientPort);
    }
}
