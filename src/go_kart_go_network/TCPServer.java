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

    public TCPServer()
    {
        // Try to open a server socket on port 5000
        try
        {
            service = new ServerSocket(ServerDetails.port);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public void listen() {
        // Create a socket object from the ServerSocket to listen and accept
        // connections. Open input and output streams
        try
        {
            server = service.accept();

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
    }

    public String getRequest() {
        try {
            requestFromClient = inputStream.readLine();
//            if ((requestFromClient = inputStream.readLine()) != null) {
//                outputStream.writeBytes(requestFromClient + "\n");
//            }
        } catch (IOException e){
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

    public void closeConnection() {
        try {
            // Comment out/remove the outputStream and server close statements if server should remain live
            outputStream.close();
            inputStream.close();
            server.close();
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}
