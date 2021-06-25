package service;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IAtomicLong;
import com.hazelcast.core.IMap;
import de.othr.vs.xml.Student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.guava.Lists;

import java.util.Collection;
import java.util.concurrent.TimeUnit;

@Path("studentaffairs")
public class StudentService {

    private static int nextStudentId = 1;
    private static HazelcastInstance hc = Hazelcast.newHazelcastInstance();

    @POST
    @Path("students")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student matriculate(Student s) {
        IMap<Integer, Student> studentDb = hc.getMap("students");
        IAtomicLong nextStudentId = hc.getAtomicLong("student_id");
        s.setMatrikelNr((int) nextStudentId.getAndIncrement());
        studentDb.put(s.getMatrikelNr(), s, 5, TimeUnit.MINUTES);
        return s;
    }

    @DELETE
    @Path("students/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student exmatriculate(@PathParam("id") int studentId) {
        IMap<Integer, Student> studentDb = hc.getMap("students");
        return studentDb.remove(studentId);
    }

    @GET
    @Path("students/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student getStudentById(@PathParam("id") int studentId) {
        IMap<Integer, Student> studentDb = hc.getMap("students");
        return studentDb.get(studentId);
    }

    @PUT
    @Path("students/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student updateStudentAccount(@PathParam("id") int studentId, Student newData) {
        IMap<Integer, Student> studentDb = hc.getMap("students");
        return studentDb.put(studentId, newData);
    }

    public Collection<Student> getAllStudents() {
        IMap<Integer, Student> studentDb = hc.getMap("students");
        return studentDb.values();
    }

    @GET
    @Path("students")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Student> getStudentsByRange(@QueryParam("from") int fromStudentId, @QueryParam("to") int toStudentId) {
        IMap<Integer, Student> studentDb = hc.getMap("students");
        if (fromStudentId == 0 && toStudentId == 0)
            return this.getAllStudents();
        else
            return Lists.newArrayList(studentDb.values().iterator()).subList(fromStudentId, toStudentId);
    }
}
