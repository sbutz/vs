package de.othr.vs.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class MessageReceiver implements Runnable {

    private Socket socket;

    public MessageReceiver(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            InputStream in = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = this.socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            while (true) {
                String line = reader.readLine();
                writer.println(">>" + line);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
