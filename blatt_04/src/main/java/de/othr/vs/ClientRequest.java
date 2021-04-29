package de.othr.vs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;


class ClientRequest implements Runnable {
    private Socket socket;
    private MessageStore store;

    ClientRequest(Socket socket, MessageStore store) {
        this.socket = socket;
        this.store = store;
    }

    @Override
    public void run() {
        try {
            InputStream in = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = this.socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            String request = reader.readLine();
            String cmd = request.substring(0, 3);
            String[] data = request.substring(3, request.length()).split("#");

            switch(cmd) {
                case "REG":
                    if (!this.store.addUser(data[0]))
                        writer.println("User already registered");
                    break;
                case "SND":
                    if (!this.store.putMessage(data[0], data[1], data[2]))
                        writer.println("Sender or receiver is not registered");
                    break;
                case "RCV":
                    String messages[] = this.store.getMessages(data[0]);
                    if (messages != null) {
                        if (messages.length == 0) {
                            writer.println("No new messages.");
                            break;
                        }

                        for (int i = 0; i < messages.length; i++)
                            writer.println(messages[i]);
                    } else {
                        writer.println("User is not registered");
                    }
                    break;
                default:
                    writer.println("Unknown Command");
                    break;
            }
            writer.flush();
        } catch(IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.socket.close();
            } catch(IOException e) {}
        }
    }
}
