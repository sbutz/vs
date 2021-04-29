package de.othr.vs;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.othr.vs.*;


class MessagingService {
    public static void main(String[] args) {
        ExecutorService es = Executors.newFixedThreadPool(10);
        MessageStore store = new MessageStore();

        try (ServerSocket serverSocket = new ServerSocket(3000)) {
            System.out.println("Server listening on port 3000");
            while (true) {
                Socket socket = serverSocket.accept();
                es.submit(new ClientRequest(socket, store));
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
