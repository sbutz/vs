package de.othr.vs;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class Client {

    public static void main(String[] args) {

        // Zu Testzwecken (lokal) natürlich auch noch ein Client :-)
        // Dieser wäre aber natürlich in einem anderen Projekt (ein Service ruft sich ja nicht selbst auf)

        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 1234)
                .usePlaintext()
                .build();

        BestellServiceGrpc.BestellServiceBlockingStub stub = BestellServiceGrpc.newBlockingStub(channel);
        Bestellung response = stub.bestellen(Bestellung.newBuilder()
                .setUserId("stefan")
                .build()
        );

        System.out.println("Antwort: " + response);
    }
}
