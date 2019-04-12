package zad1;

import javax.swing.*;
import java.net.*;
import java.io.*;

public class Client {

    private Socket socketSend = null;
    private Socket waitForConnect = null;
    private ServerSocket socketCome = null;
    private DataInputStream input = null;
    private DataInputStream inputCome = null;
    private DataOutputStream out = null;


    public Client(String address, int port) {

        try {
            socketSend = new Socket(address, port);
            System.out.println("Connected");
            input = new DataInputStream(System.in);
            out = new DataOutputStream(socketSend.getOutputStream());

            String line = "";


            while (!line.equals("Over")) {
                //Jezyk;Slowo;Port
                socketCome = new ServerSocket(5004);
                //line = input.readLine();
                line = Window.intialize() + ";" + socketCome.getLocalPort();
                if (line.length() < 10){
                    line = "Over";
                }
                out.writeUTF(line);
                //Oczekiwanie na odpowiedz
                waitForConnect = socketCome.accept();
                inputCome = new DataInputStream(
                        new BufferedInputStream(waitForConnect.getInputStream()));

                line = inputCome.readUTF();
                JOptionPane.showMessageDialog(null, line, "InfoBox", JOptionPane.INFORMATION_MESSAGE);
                System.out.println(line);
                socketCome.close();
            }

            inputCome.close();
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
