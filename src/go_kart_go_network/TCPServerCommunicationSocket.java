package go_kart_go_network;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServerCommunicationSocket {

    ServerSocket socket = null;
    Socket clientSocket = null;

    // Declare an input stream and String to store message from client
    private BufferedReader inputStream;

    private ObjectInput inputObject = null;

    // Declare an output stream to client
    private DataOutputStream outputStream;

    public TCPServerCommunicationSocket() {
        // Try to open a server socket on port 5000
        try {
            socket = new ServerSocket(ServerDetails.port);
        } catch (IOException e) {
            System.err.println("I/O error");
        }
    }

    public void listen() {
        try {
            clientSocket = socket.accept();
            openInputOutputChannels();
        } catch (IOException e) {
            System.err.println("I/O error");
        }
    }

    public void openInputOutputChannels() {
        // Create a socket object from the ServerSocket to listen and accept
        // connections. Open input and output streams
        try {
            // channel for incoming text
            inputStream = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            // channel for incoming objects
            inputObject = new ObjectInputStream(clientSocket.getInputStream());

            // channel to write back to client
            outputStream = new DataOutputStream(clientSocket.getOutputStream());
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public String getMessage() {
        String message = null;
        try {
            message = inputStream.readLine();
        } catch (IOException e) {
            System.err.println("I/O error");
        }
        return message;
    }

    public Object getKart() {
        Object object = null;
        try {
            object = inputObject.readObject();
        } catch (IOException e) {
            System.err.println("I/O error");
        } catch (ClassNotFoundException e) {
            // do something
        }
        return object;
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

    public void respond(String message) {
        try {
            outputStream.writeBytes(message + "\n");
        } catch (IOException e) {
            System.err.println("I/O error");
        }
    }

    public int returnClientPort() {
        return clientSocket.getPort();
    }
}