package it.unisannio.gruppo3.teacher.presentation;

import it.unisannio.gruppo3.entities.Teacher;
import it.unisannio.gruppo3.teacher.logic.TeacherLogic;
import it.unisannio.gruppo3.teacher.logic.TeacherLogicImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/teacherService")

public class TeacherService {
    TeacherLogic logic;

    public TeacherService(){
        logic = new TeacherLogicImpl();
    }
    
    @POST
    public Response createTeacher(Teacher teacher){
        Long createdTeacherId = logic.createTeacher(teacher);
        if(createdTeacherId != null){
            URI uri = UriBuilder.fromPath("/teacher/teacherService/{id}").build(createdTeacherId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getTeacher(@PathParam("id") Long id){
        Teacher teacherById = logic.getTeacher(id);
        if (teacherById != null){
            return Response.ok(teacherById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}
