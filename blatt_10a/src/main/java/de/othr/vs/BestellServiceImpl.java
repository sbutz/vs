package de.othr.vs;

import com.google.protobuf.Empty;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IMap;
import io.grpc.stub.StreamObserver;


public class BestellServiceImpl extends BestellServiceGrpc.BestellServiceImplBase {

    @Override
    public void bestellen(Bestellung bestellung, StreamObserver<Bestellung> responseObserver) {
        IMap<Integer, Bestellung> bestellungen = ServerInstance.hazelcast.getMap("bestellungen");
        IAtomicLong lastBestellId = ServerInstance.hazelcast.getAtomicLong("bestell_id");

        Bestellung b = Bestellung.newBuilder(bestellung)
                .setId((int) lastBestellId.incrementAndGet())
                .build();
        bestellungen.put(b.getId(), b);

        responseObserver.onNext(b);
        responseObserver.onCompleted();
    }

    @Override
    public void auflisten(Empty request, StreamObserver<Bestellung> responseObserver) {
        IMap<Integer, Bestellung> bestellungen = ServerInstance.hazelcast.getMap("bestellungen");
        for (Bestellung b: bestellungen.values()) {
            responseObserver.onNext(b);
        }
        responseObserver.onCompleted();
    }

    @Override
    public void loeschen(Bestellung request, StreamObserver<Bestellung> responseObserver) {
        IMap<Integer, Bestellung> bestellungen = ServerInstance.hazelcast.getMap("bestellungen");
        Bestellung b = bestellungen.remove(request.getId());
        responseObserver.onNext(b);
        responseObserver.onCompleted();
    }
}
