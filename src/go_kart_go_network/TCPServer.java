package go_kart_go_network;

import java.io.*;
import java.net.*;

public class TCPServer
{
    // Declare a server socket and a client socket for the server
    ServerSocket service;
    Socket server;
    // Declare an input stream and String to store message from client
    BufferedReader inputStream;
    String requestFromClient;
    // Declare an output stream to client
    DataOutputStream outputStream;
    ObjectInput input;

    public TCPServer(Socket socket) {
        this.server = socket;
    }

    public Socket OpenInputOutputStreams() {
        // Open input and output streams
        try
        {
            inputStream = new BufferedReader(
                    new InputStreamReader(
                            server.getInputStream()
                    )
            );

            outputStream = new DataOutputStream(
                    server.getOutputStream()
            );

            input = new ObjectInputStream(
                    server.getInputStream()
            );

        }
        catch (IOException e)
        {
            System.out.println(e);
        }

        return server;
    }

    public String getRequest() {
        try {
            requestFromClient = inputStream.readLine();
        } catch (SocketException e){
            requestFromClient = Messages.closeConnection;
        } catch (IOException e) {
            System.err.println(e);
        }
        return requestFromClient;
    }

    public void sendResponse(String response) {
        try {
            outputStream.writeBytes(response + "\n");
        } catch (IOException e){
            System.err.println(e);
        }
    }

    public Object getObject() {
        try {
            return input.readObject();
        } catch (ClassNotFoundException e) {
            System.err.println(e);
        } catch (IOException e) {
            System.err.println(e);
        }
        return new Object();
    }

    public void sendObject(Object object) {
        // Write data to the socket
    }

    public void closeConnection() {
        try {
            outputStream.close();
            inputStream.close();
            server.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
