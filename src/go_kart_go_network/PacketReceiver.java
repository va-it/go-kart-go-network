package go_kart_go_network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PacketReceiver {

    public static DatagramPacket packet;

    public static String senderAddress;
    public static InetAddress senderInetAddress;
    public static int senderPort;

    public static String messageReceived;

    public static String receivePacket(DatagramSocket socket) {

        try {
            // Create a datagram packet containing the byte array
            packet = new DatagramPacket( new byte[256], 256 );

            socket.receive( packet ); // app will listen and wait

            senderInetAddress = packet.getAddress();
            senderAddress = packet.getAddress().getHostAddress();
            senderPort = packet.getPort();

            System.out.println ("Packet received from: " + senderAddress + ":" + senderPort + "\n");

            ByteArrayInputStream bytesIn = new ByteArrayInputStream( packet.getData() );

            for (int i=0; i < packet.getLength(); i++)
            {
                int data = bytesIn.read();

                if ( data == -1 ) {
                    break;
                }
                else {
                    // build a string with each character
                    messageReceived += (char) data;
                }
            }

            return messageReceived;

        } catch( UnknownHostException e )
        {
            System.err.println ("Can't find host " + senderAddress );
        }
        catch( IOException e )
        {
            System.err.println ("Error - " + e );
        }

        return messageReceived;
    }
}
