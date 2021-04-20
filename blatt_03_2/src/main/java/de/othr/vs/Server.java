package de.othr.vs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import de.othr.vs.*;

public class Server {

    public static void main(String[] args) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(3000);
            System.out.println("Warte auf Verbindungen...");

            while (true) {
                Socket s = serverSocket.accept();
                System.out.println("Neue Verbindung von " + s.getRemoteSocketAddress());

                Thread t = new Thread(new ConnectionHandler(s));
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
