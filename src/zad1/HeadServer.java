package zad1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;


public class HeadServer {
    //initialize socket and input stream
    private Socket socket = null;
    private Socket socketSend = null;
    private ServerSocket server = null;
    private DataInputStream inServer = null;
    private DataInputStream inputClient = null;
    private DataOutputStream outputClient = null;

    public static Map<String, Integer> mapServer = Map.of("RUS", 5001, "FR", 5002, "ANG", 5003);

    public void server(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");
            socket = server.accept();
            inServer = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            System.out.println("Client accepted");
            String line = "";
            while (!line.equals("Over")) {
                line = inServer.readUTF();
                System.out.println(line);
                String[] splitLine = line.split(";");

                System.out.println();
                try {
                    socketSend = new Socket("localhost", mapServer.get(splitLine[0]));
                    inputClient = new DataInputStream(System.in);
                    outputClient = new DataOutputStream(socketSend.getOutputStream());
                    outputClient.writeUTF(splitLine[1] + ";" + splitLine[2]);
                }catch (NullPointerException a){
                    System.out.println("Closing connection");
                }
            }
            socket.close();
            inServer.close();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String args[]) {
        int headServerPort = 5000;
        HeadServer headServer = new HeadServer();
        headServer.server(headServerPort);
    }
}
