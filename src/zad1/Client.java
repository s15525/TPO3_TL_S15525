package zad1;

import java.net.*;
import java.io.*;

public class Client
{

    private Socket socketSend = null;
    private Socket socketCome = null;
    private DataInputStream  input   = null;
    private DataOutputStream out     = null;


    public Client(String address, int port)
    {

        try
        {
            socketSend = new Socket(address, port);
            System.out.println("Connected");
            input  = new DataInputStream(System.in);
            out    = new DataOutputStream(socketSend.getOutputStream());

        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }


        String line = "";


        while (!line.equals("Over"))
        {
            try
            {
                line = input.readLine();
                out.writeUTF(line);
            }
            catch(IOException i)
            {
                System.out.println(i);
            }
        }

        try
        {
            input.close();
            out.close();
            socketSend.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        Client client = new Client("localhost", 5000);
    }
}
