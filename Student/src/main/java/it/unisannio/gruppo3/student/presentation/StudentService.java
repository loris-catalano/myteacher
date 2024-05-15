package it.unisannio.gruppo3.student.presentation;

import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.student.logic.*;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.*;

import jakarta.ws.rs.*;

import java.net.URI;
import java.util.ArrayList;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/studs")
public class StudentService {
    StudentLogic logic;


    public StudentService(){
        logic = new StudentLogicImpl();
    }

    @POST
    public Response createStudent(Student student){
        Long createdStudentId = logic.createStudent(student);
        if(createdStudentId != null){
            URI uri = UriBuilder.fromPath("/student/studs/{id}").build(createdStudentId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getStudent(@PathParam("id") Long id){
        Student studentById = logic.getStudent(id);
        if (studentById != null){
            return Response.ok(studentById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();

    }

}
