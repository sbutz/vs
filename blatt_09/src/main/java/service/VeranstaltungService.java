package service;

import app.Server;
import app.mapreduce.TippCollator;
import app.mapreduce.TippMapper;
import app.mapreduce.TippReducerFactory;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.ICompletableFuture;
import com.hazelcast.core.IMap;
import com.hazelcast.mapreduce.Job;
import com.hazelcast.mapreduce.JobTracker;
import com.hazelcast.mapreduce.KeyValueSource;
import de.othr.vs.xml.Veranstaltung;
import jakarta.ws.rs.*;

import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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
        IMap<String, Veranstaltung> eventDb = Server.hazelcast.getMap("events");

        if (tipps.length == 0)
            return eventDb.values();

        JobTracker jobTracker = Server.hazelcast.getJobTracker("default");
        KeyValueSource<String, Veranstaltung> source = KeyValueSource.fromMap(eventDb);
        Job<String, Veranstaltung> job = jobTracker.newJob(source);
        ICompletableFuture<List<Veranstaltung>> future = job
                .mapper(new TippMapper(tipps))
                .reducer(new TippReducerFactory())
                .submit(new TippCollator());

        try {
            return future.get(10, TimeUnit.SECONDS);

        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        } catch (ExecutionException e) {
            e.printStackTrace();
            return null;
        } catch (TimeoutException e) {
            e.printStackTrace();
            return null;
        }
    }
}
