package de.othr.vs.server;

import java.io.IOException;
import java.net.Socket;
import java.net.ServerSocket;
import java.net.UnknownHostException;

import de.othr.vs.client.*;


public class Main {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Server listening...");

            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("Client connected " + s.getRemoteSocketAddress());

                new Thread(new MessageSender(s)).start();
                new Thread(new MessageReceiver(s)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
