package de.othr.vs.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class MessageReceiver implements Runnable {

    private Socket socket;

    public MessageReceiver(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            InputStream in = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            while (true) {
                String line = reader.readLine();

                System.out.println(">" + line);
                System.out.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
