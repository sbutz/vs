package de.othr.vs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {
        try {
            Socket s = new Socket("localhost", 3000);

            InputStream in = s.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(in));

            OutputStream out = s.getOutputStream();
            PrintWriter writer = new PrintWriter(out);

            System.out.println("Bitte geben Sie eine Nachricht ein:");
            Scanner scanner = new Scanner(System.in);
            String line = scanner.nextLine();
            scanner.close();

            writer.println(line);
            writer.flush();

            line = reader.readLine();
            System.out.println("Antwort vom Server: " + line);

            s.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
