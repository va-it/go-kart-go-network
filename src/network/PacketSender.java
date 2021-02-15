package network;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PacketSender {

    static String messageToSend;
    static InetAddress receiverAddress;
    static int receiverPort;

    static DatagramSocket socket;
    static DatagramPacket packet;

    private static void sendPacket()
    {
        try {
            // Create a message to send using a UDP packet
            ByteArrayOutputStream bytesOut = new ByteArrayOutputStream();
            PrintStream pout = new PrintStream( bytesOut );
            pout.print(messageToSend);

            // Get contents of message as an array of bytes
            byte[] bytesArray = bytesOut.toByteArray();

            // Create a datagram packet containing the byte array
            packet = new DatagramPacket( bytesArray, bytesArray.length );

            // Address packet to sender
            packet.setAddress( receiverAddress );

            // Set port number to 2000
            packet.setPort( receiverPort );

            // Send the packet - no guarantee  of delivery
            socket.send(packet);
        }
        catch( UnknownHostException e )
        {
            System.err.println ("Can't find host " + receiverAddress );
        }
        catch( IOException e )
        {
            System.err.println ("Error - " + e );
        }
    }
}
