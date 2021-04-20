package de.othr.vs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server {

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(3000);
            System.out.println("Warte auf Verbindungen...");

            while(true) {
                Socket s = serverSocket.accept();
                System.out.println("Neue Verbindung von " + s.getRemoteSocketAddress());

                InputStream in = s.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                OutputStream out = s.getOutputStream();
                PrintWriter writer = new PrintWriter(out);

                String line = reader.readLine();
                System.out.println(line);

                writer.println("*" + line + "*");
                writer.flush();
                s.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
