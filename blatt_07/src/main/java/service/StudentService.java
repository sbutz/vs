package service;

import entity.Student;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.glassfish.jersey.internal.guava.Lists;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Path("studentaffairs")
public class StudentService {

    private static int nextStudentId = 1;
    private static Map<Integer, Student> studentDb = new HashMap<>();

    @POST
    @Path("students")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student matriculate(Student s) {
        s.setMatrikelNr(nextStudentId);
        studentDb.put(nextStudentId, s);
        nextStudentId++;
        return s;
    }

    @DELETE
    @Path("students/{id}")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student exmatriculate(@PathParam("id") int studentId) {
        return studentDb.remove(studentId);
    }

    @GET
    @Path("students/{id}")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Student getStudentById(@PathParam("id") int studentId) {
        return studentDb.get(studentId);
    }

    @PUT
    @Path("students/{id}")
    @Consumes({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Student updateStudentAccount(@PathParam("id") int studentId, Student newData) {
        return studentDb.put(studentId, newData);
    }

    //@GET
    //@Path("students")
    //@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Student> getAllStudents() {
        return studentDb.values();
    }

    @GET
    @Path("students")
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public Collection<Student> getStudentsByRange(@QueryParam("from") int fromStudentId, @QueryParam("to") int toStudentId) {
        return Lists.newArrayList(studentDb.values().iterator()).subList(fromStudentId, toStudentId);
    }
}
