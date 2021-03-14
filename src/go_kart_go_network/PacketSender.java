package go_kart_go_network;

import java.io.*;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class PacketSender {

    static DatagramPacket packet;

    public static void sendPacket(
            String messageToSend, InetAddress receiverAddress, int receiverPort, DatagramSocket socket
    ) {
        try {
            // Create a message to send using a UDP packet
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            PrintStream printStream = new PrintStream( byteArrayOutputStream );
            printStream.print(messageToSend);

            // Get contents of message as an array of bytes
            byte[] bytesArray = byteArrayOutputStream.toByteArray();

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

    public static void sendPacketWithObject(
            Object objectToSend, InetAddress receiverAddress, int receiverPort, DatagramSocket socket
    ) {
        try {
            // Serialize to a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutput objectOutput = new ObjectOutputStream(byteArrayOutputStream);
            objectOutput.writeObject(objectToSend);
            objectOutput.close();


            // Get contents of message as an array of bytes
            byte[] bytesArray = byteArrayOutputStream.toByteArray();

            // Create a datagram packet containing the byte array
            packet = new DatagramPacket( bytesArray, bytesArray.length );

            // Address packet to sender
            packet.setAddress( receiverAddress );

            // Set port number to 2000
            packet.setPort( receiverPort );

            // set buffer size
            socket.setSendBufferSize(socket.getSendBufferSize());

            // Send the packet - no guarantee  of delivery
            socket.send(packet);
        }
        catch( UnknownHostException e )
        {
            System.err.println ("Can't find host " + receiverAddress );
        }
        catch (IllegalArgumentException e) {
            // sometimes the port in the packet seems to be out of range...
            System.err.println ("Port out of range: " + e );
        }
        catch (NullPointerException e) {
            System.err.println ("Object is null: " + e );
        }
        catch( IOException e )
        {
            System.err.println ("Error - " + e );
        }
    }
}
