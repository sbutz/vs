package de.othr.vs.server;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


public class MessageSender implements Runnable {

    private Socket socket;

    public MessageSender(Socket s) {
        this.socket = s;
    }

    public void run() {
        try {
            OutputStream out = this.socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            writer.println("Hi, I'm fervent hypatia. Nice you made it so far.");
            writer.println("Please tell me your name!");
            writer.flush();

            while (true) {
                Thread.sleep(10*1000);
                writer.println("Still there?");
                writer.flush();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
