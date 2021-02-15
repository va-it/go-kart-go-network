package go_kart_go_network;

public class Main {
    public static void main (String args[])
    {
        PacketSender packetSender = new PacketSender();
        PacketReceiver packetReceiver = new PacketReceiver();
        Server.getAddrress();
        System.out.println(Server.address);
    }
}