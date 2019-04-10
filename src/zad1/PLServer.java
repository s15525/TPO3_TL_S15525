package zad1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class PLServer {
    private Socket socket = null;
    private Socket socketSend = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public void server(int port) {

        try {
            server = new ServerSocket(port);
            socket = server.accept();

            in = new DataInputStream(
                    new BufferedInputStream(socket.getInputStream()));

            String line = "";


            line = in.readUTF();
            System.out.println(line);
            System.out.println("Closing connection");

            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);

        }
    }

    public static void main(String args[]) {
        int headServerPort = 5001;
        HeadServer headServer = new HeadServer();
        headServer.server(headServerPort);
    }
}
