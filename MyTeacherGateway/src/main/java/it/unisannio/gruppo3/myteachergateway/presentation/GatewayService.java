package it.unisannio.gruppo3.myteachergateway.presentation;

import it.unisannio.gruppo3.entities.Student;
import it.unisannio.gruppo3.entities.Teacher;
import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;
import it.unisannio.gruppo3.myteachergateway.logic.GatewayLogic;
import it.unisannio.gruppo3.myteachergateway.logic.GatewayLogicImpl;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/")

public class GatewayService {
    GatewayLogic logic;

    public GatewayService() {
        logic = new GatewayLogicImpl();
    }

    @GET
    @Path("/student/studentService/{id}")
    @RolesAllowed({"STUDENT", "TEACHER"})
    public Response getStudent(@PathParam("id") Long id) {
        Student student = logic.getStudent(id);
        return Response.ok(student).build();
    }

    @GET
    @Path("/teacher/teacherService/{id}")
    @RolesAllowed({"STUDENT"})
    public Response getTeacher(@PathParam("id") Long id) {
        Teacher teacher = logic.getTeacher(id);
        return Response.ok(teacher).build();
    }
}
