package de.othr.vs.client;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;

import de.othr.vs.client.*;


public class Main {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 3000);
            System.out.println("Connected");

            new Thread(new MessageSender(socket)).start();
            new Thread(new MessageReceiver(socket)).start();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
