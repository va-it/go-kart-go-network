package go_kart_go_network;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;

public class TCPClient
{
    Socket clientSocket;
    DataOutputStream outputStream;
    // used to send over the kart
    ObjectOutput objectOutput;
    // used to get the answer back from server
    BufferedReader inputStream;

    public TCPClient() {
        try {
            clientSocket = new Socket(ServerDetails.getAddress(), ServerDetails.port);

            outputStream = new DataOutputStream(clientSocket.getOutputStream());

            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            objectOutput = new ObjectOutputStream(clientSocket.getOutputStream());
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host: " + ServerDetails.hostName);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to: " + ServerDetails.hostName);
        }
    }

    public void sendRequest(String request) {
        // Write data to the socket
        if (clientSocket != null && outputStream != null && inputStream != null && objectOutput != null) {
            try {
                outputStream.writeBytes( request + "\n" );
            } catch (SocketException e) {
                System.err.println("Cannot reach server: " + e);
            } catch (IOException e) {
                System.err.println("Send request error: " + e);
            }
        }
    }

    public String getResponse() {
        try {
            return inputStream.readLine();
        } catch (IOException e){
            System.err.println("Get response error: " + e);
        }
        return Messages.error;
    }

    // unused, but available in case karts from client need to be sent over TCP
    public void sendObject(Object object) {
        // Write data to the socket
        if (clientSocket != null && outputStream != null && inputStream != null && objectOutput != null) {
            try {
                objectOutput.writeObject(object);
                objectOutput.flush();
            } catch (IOException e) {
                System.err.println(e);
            }
        }
    }

    // unused, but available in case connection to the server has to be closed explicitly
    public void closeConnection() {
        try {
            // close the input/output streams and socket
            outputStream.close();
            inputStream.close();
            clientSocket.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
