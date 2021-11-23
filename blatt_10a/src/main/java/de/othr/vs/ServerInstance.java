package de.othr.vs;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import io.grpc.Server;
import io.grpc.ServerBuilder;

import java.io.IOException;

public class ServerInstance {

    public static HazelcastInstance hazelcast;

    public static void main(String[] args) throws IOException, InterruptedException {
        hazelcast = Hazelcast.newHazelcastInstance();

        Server server = ServerBuilder.forPort(1234)
                .addService(new BestellServiceImpl())
                .build();
        
        server.start();
        
        System.out.println("Server running...");
        
        server.awaitTermination();
    }
}
