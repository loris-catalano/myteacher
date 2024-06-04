package it.unisannio.gruppo3.chat.Presentation;

import it.unisannio.gruppo3.chat.Logic.ChatLogic;
import it.unisannio.gruppo3.chat.Logic.ChatLogicImpl;
import it.unisannio.gruppo3.entities.Chat;
import it.unisannio.gruppo3.entities.Lesson;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/chatService")
public class ChatService {
    private ChatLogic logic;

    public ChatService(){
        logic = new ChatLogicImpl();
    }

    @POST
    public Response createChat(Chat chat){
        Long createdChatId = logic.createChat(chat);
        if(createdChatId != null){
            URI uri = UriBuilder.fromPath("/chat/chatService/{id}").build(createdChatId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getChat(@PathParam("id") Long id){
        Chat chatById = logic.getChat(id);
        if (chatById != null){
            return Response.ok(chatById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @PUT
    public Response updateChat(Chat chat) {
        Chat chatUpdated = logic.updateChat(chat);
        return Response.ok(chatUpdated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteChat(@PathParam("id") Long id) {
        boolean isDeleted = logic.deleteChat(id);
        if (isDeleted){
            return Response.noContent().build();
        } else {
            return Response.serverError().build();
        }
    }
}
