package de.othr.vs.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;


public class MessageSender implements Runnable {

    private Socket socket;

    public MessageSender(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(System.in);
        try {
            OutputStream out = this.socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            while (true) {
                String line = scanner.nextLine();

                writer.println(line);
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
