package go_kart_go_network;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class ClientCommunicationSocket {
    // Declare client socket
    Socket clientSocket = null;

    // Declare input stream from server and string to store input received from server
    BufferedReader inputStream = null;
    // Declare output stream and string to send to server
    DataOutputStream outputStream = null;

    ObjectOutput output = null;

    public ClientCommunicationSocket() {
        try {
            clientSocket = new Socket(ServerDetails.hostName, ServerDetails.port);

            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // for text
            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            // for Objects
            output = new ObjectOutputStream(clientSocket.getOutputStream());

        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + ServerDetails.hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + ServerDetails.hostName);
        } catch (SecurityException e) {
            System.err.println("Couldn't securely open socket");
        }
    }

    public void closeCommunication() {
        try {
            outputStream.close();
            inputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("I/O error");
        }
    }

    public void sendMessage(String message) {
        try {
            outputStream.writeBytes( message + "\n" );
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }
    }
}
