package zad1;

import java.net.*;
import java.io.*;

public class Client {

    private Socket socketSend = null;
    private Socket waitForConnect = null;
    private ServerSocket socketCome = null;
    private DataInputStream input = null;
    private DataInputStream input2 = null;
    private DataOutputStream out = null;


    public Client(String address, int port) {

        try {
            socketSend = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socketSend.getOutputStream());
            socketCome = new ServerSocket(5002);
            String line = "";


            while (!line.equals("Over")) {
                //Jezyk;Slowo;Port

                line = input.readLine();
                line = line + ";" + socketCome.getLocalPort();
                out.writeUTF(line);
                //Oczekiwanie na odpowiedz
                waitForConnect = socketCome.accept();
                input2 = new DataInputStream(
                        new BufferedInputStream(waitForConnect.getInputStream()));

                line = input2.readUTF();
                System.out.println(line);
            }

            input2.close();
            input.close();
            out.close();
            socketCome.close();
            socketSend.close();
        } catch (IOException i) {
            System.out.println(i);
        }

    }

    public static void main(String args[]) {
        Client client = new Client("localhost", 5000);
    }
}
