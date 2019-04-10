package zad1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;


public class HeadServer {
    //initialize socket and input stream
    private Socket socket = null;
    private Socket socketSend = null;
    private ServerSocket server = null;
    private DataInputStream inServer = null;
    private DataInputStream  inputClient   = null;
    private DataOutputStream outputClient     = null;

    private Map<String,Integer> mapServer = Map.of("Rosyjski",5001);

    public void server(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server started");

            System.out.println("Waiting for a client ...");

            socket = server.accept();
            System.out.println("Client accepted");

            inServer = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));


            String line = "";
            line = inServer.readUTF();
            System.out.println(line);

            //serwer staje sie klientem
            socketSend = new Socket("localhost", 5001);
            inputClient = new DataInputStream(System.in);
            outputClient = new DataOutputStream(socketSend.getOutputStream());
            outputClient.writeUTF(line);
            System.out.println("Closing connection");

            socket.close();
            inServer.close();

        }catch (IOException i) {
            System.out.println(i);
        }
    }
    public static void main (String args[]){
        int headServerPort = 5000;
        HeadServer headServer = new HeadServer();
        headServer.server(headServerPort);
    }
}
