package de.othr.vs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class ConnectionHandler implements Runnable {

    private Socket socket;

    public ConnectionHandler(Socket s) {
        this.socket = s;
    }

    @Override
    public void run() {
        try {
            InputStream in = this.socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = this.socket.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            String req = reader.readLine();
            String[] parts = req.split(" ");

            if (!parts[0].equals("GET") || !parts[2].equals("HTTP/1.1")) {
                writer.println("HTTP/1.1 400 Bad Request");
                writer.flush();
                return;
            }

            Path p = Paths.get("./src", parts[1]);
            if (!p.toFile().exists()) {
                writer.println("HTTP/1.1 400 Bad Request");
                writer.flush();
                return;
            }

            List<String> contents = Files.readAllLines(p);
            String content = String.join("", contents);

            writer.print("HTTP/1.1 200 OK\r\n");
            writer.print("Content-Type: text/html\r\n");
            writer.print("Content-Length: " + content.length() + "\r\n");
            writer.print("\r\n");
            writer.print(content);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                this.socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
