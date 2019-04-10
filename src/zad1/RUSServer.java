package zad1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class RUSServer {
    private Socket socket = null;
    private Socket socketSend = null;
    private ServerSocket server = null;
    private DataInputStream in = null;

    public void server(int port) {

        try {
            server = new ServerSocket(port);



            String line = "";

            while (!line.equals("Over")) {

                socket = server.accept();
                in = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));

                line = in.readUTF();
                System.out.println(line);

            }
            System.out.println("Closing connection");
            socket.close();
            in.close();
        } catch (IOException i) {
            System.out.println(i);

        }
    }

    public static void main(String args[]) {
        int RUSServerPort = 5001;
        RUSServer rusServer = new RUSServer();
        rusServer.server(RUSServerPort);
    }
}
