package zad1;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;

public class ANGServer {
    //initialize socket and input stream
    private Socket socket = null;
    private Socket socketSend = null;
    private ServerSocket server = null;
    private DataInputStream inServer = null;
    private DataInputStream inputServer = null;
    private DataOutputStream outputServer = null;

    private Map<String, String> mapWords = Map.of("Pies", "Dog", "Dziewczyna", "Girl", "Chlopak", "Boy", "Warszwa", "Warsaw");

    public void server(int port) {

        try {
            server = new ServerSocket(port);
            System.out.println("Server started");
            System.out.println("Waiting for a client ...");

            String line = "";
            while (!line.equals("Over")) {
                socket = server.accept();
                System.out.println("Client accepted");
                inServer = new DataInputStream(
                        new BufferedInputStream(socket.getInputStream()));
                line = inServer.readUTF();
                String[] splitLine = line.split(";");
                //serwer staje sie klientem
                socketSend = new Socket("localhost", Integer.parseInt(splitLine[1]));
                inputServer = new DataInputStream(System.in);
                outputServer = new DataOutputStream(socketSend.getOutputStream());
                try {
                    outputServer.writeUTF(mapWords.get(splitLine[0]));
                }catch (NullPointerException a){
                    outputServer.writeUTF("Slowa nie znaleziono w slowniku Angielskim");
                }

            }
            System.out.println("Closing connection");
            socket.close();
            inServer.close();

        } catch (IOException i) {
            System.out.println(i);
        }
    }

    public static void main(String[] arg){
        int ANGServerPort = 5003;
        ANGServer angServer = new ANGServer();
        angServer.server(ANGServerPort);
    }
}
