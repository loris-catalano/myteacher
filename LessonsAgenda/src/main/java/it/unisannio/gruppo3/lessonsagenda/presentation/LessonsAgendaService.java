package it.unisannio.gruppo3.lessonsagenda.presentation;
import it.unisannio.gruppo3.entities.LessonsAgenda;
import it.unisannio.gruppo3.lessonsagenda.logic.*;
import jakarta.ws.rs.core.*;

import jakarta.ws.rs.*;

import java.net.URI;
import java.util.ArrayList;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/lessonsAgendaService")
public class LessonsAgendaService {
    LessonsAgendaLogic logic;


    public LessonsAgendaService() {
        logic = new LessonsAgendaLogicImpl();
    }

    @POST
    public Response createLessonsAgenda(LessonsAgenda lessonsAgenda) {
        Long createdLessonsAgendaId = logic.createLessonsAgenda(lessonsAgenda);
        if (createdLessonsAgendaId != null) {
            URI uri = UriBuilder.fromPath("/lessonsAgenda/lessonsAgendaService/{id}").build(createdLessonsAgendaId);
            return Response.created(uri).build();
        } else {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getLessonsAgenda(@PathParam("id") Long id) {
        LessonsAgenda lessonsAgendaById = logic.getLessonsAgenda(id);
        if (lessonsAgendaById != null) {
            return Response.ok(lessonsAgendaById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAllLessonsAgendas() {
        ArrayList<LessonsAgenda> lessonsAgendas = logic.getAllLessonsAgendas();
        return Response.ok(lessonsAgendas).build();
    }

    @PUT
    public Response updateLessonsAgenda(LessonsAgenda lessonsAgenda) {
        LessonsAgenda lessonsAgendaUpdated = logic.updateLessonsAgenda(lessonsAgenda);
        return Response.ok(lessonsAgendaUpdated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteLessonsAgenda(@PathParam("id") Long id) {
        boolean isDeleted = logic.deleteLessonsAgenda(id);
        if (isDeleted) {
            return Response.noContent().build();
        } else {
            return Response.serverError().build();
        }
    }
}
