package service;

import app.Server;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IMap;
import de.othr.vs.xml.Veranstaltung;
import jakarta.ws.rs.*;

import java.util.Collection;
import java.util.UUID;

@Path("events")
public class VeranstaltungService {

    @POST
    public Veranstaltung anlegen(Veranstaltung v) {
        IMap<String, Veranstaltung> eventDb =
                Server.hazelcast.getMap("events");
        v.setId(UUID.randomUUID().toString());
        eventDb.put(v.getId(), v);
        return v;
    }

    @GET
    @Path("/{uuid}")
    public Veranstaltung abfragen(@PathParam("uuid") String uuid) {
        IMap<String, Veranstaltung> eventDb =
                Server.hazelcast.getMap("events");
        return eventDb.get(uuid);
    }

    @GET
    public Collection<Veranstaltung> finden(@QueryParam("tipps") String[] tipps) {
        IMap<String, Veranstaltung> eventDb =
                Server.hazelcast.getMap("events");
        return eventDb.values();
    }
}
