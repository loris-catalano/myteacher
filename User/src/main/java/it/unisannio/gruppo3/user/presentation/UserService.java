package it.unisannio.gruppo3.user.presentation;

import it.unisannio.gruppo3.entities.User;
import it.unisannio.gruppo3.user.logic.*;
import jakarta.ws.rs.core.*;

import jakarta.ws.rs.*;

import java.net.URI;
import java.util.ArrayList;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/userService")
public class UserService {
    UserLogic logic;


    public UserService(){
        logic = new UserLogicImpl();
    }

    @POST
    public Response createUser(User user){
        Long createdUserId = logic.createUser(user);
        if(createdUserId != null){
            URI uri = UriBuilder.fromPath("/user/userService/{id}").build(createdUserId);
            return Response.created(uri).build();
        } else{
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response getUser(@PathParam("id") Long id){
        User userById = logic.getUser(id);
        if (userById != null){
            return Response.ok(userById).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAllUsers() {
        ArrayList<User> users = logic.getAllUsers();
        return Response.ok(users).build();
    }

    @PUT
    public Response updateUser(User user) {
        User userUpdated = logic.updateUser(user);
        return Response.ok(userUpdated).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") Long id) {
        boolean isDeleted = logic.deleteUser(id);
        if (isDeleted){
            return Response.noContent().build();
        } else {
            return Response.serverError().build();
        }
    }

}
