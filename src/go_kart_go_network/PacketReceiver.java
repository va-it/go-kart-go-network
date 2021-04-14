package go_kart_go_network;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.*;

public class PacketReceiver {

    public DatagramPacket packet;

    public String receivePacket(DatagramSocket socket, boolean server) {
        String messageReceived = "";

        if (!server) {
            try {
                // stop listening for a response after ~33 milliseconds
                socket.setSoTimeout(1000/30);
            } catch (SocketException e) {
                System.err.println("Socket error: " + e);
            }
        }

        try {
            // Create a datagram packet containing the byte array
            packet = new DatagramPacket( new byte[256], 256 );

            socket.receive( packet ); // app will listen and wait

            // String built as shown in tutorial below
            // https://docs.oracle.com/javase/tutorial/networking/datagrams/clientServer.html
            return new String(packet.getData(), 0, packet.getLength());

        } catch( UnknownHostException e ) {
            System.err.println ("Can't find host: " + e);
        } catch (SocketTimeoutException e) {
            return Messages.timeout;
        } catch( IOException e ) {
            System.err.println ("Error: " + e );
        }

        return messageReceived;
    }


    public Object receiveObject(DatagramSocket socket) {

        try {
            // stop listening for an object after ~33 milliseconds
            socket.setSoTimeout(1000/30);
        } catch (SocketException e) {
            System.err.println("Socket error: " + e);
        }

        try {

            int bufferSize = socket.getReceiveBufferSize();

            // Create a datagram packet containing the byte array
            packet = new DatagramPacket( new byte[bufferSize], bufferSize );

            socket.receive( packet ); // app will listen and wait

            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(packet.getData()));
            return objectInputStream.readObject();

        } catch( UnknownHostException e ) {
            System.err.println ("Can't find host: " + e);
        }
        catch( IOException e ) {
            System.err.println ("Error: " + e );
        }
        catch (ClassNotFoundException e) {
            System.err.println ("Error class not found: " + e );
        }

        return null;
    }
}
