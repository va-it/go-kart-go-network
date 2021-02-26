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

        } catch( UnknownHostException e ) {
            System.err.println ("Can't find host: " + e);
        } catch (SocketTimeoutException e) {
            messageReceived = Messages.timeout;
        }
        catch( IOException e ) {
            System.err.println ("Error: " + e );
        }

        return messageReceived;
    }


    public Object receiveObject(DatagramSocket socket) {

        Object objectReceived = null;

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

        } catch( UnknownHostException e )
        {
            System.err.println ("Can't find host: " + e);
        }
        catch( IOException e )
        {
            System.err.println ("Error: " + e );
        }
        catch (ClassNotFoundException e) {
            System.err.println ("Error: " + e );
        }

        return objectReceived;
    }
}
