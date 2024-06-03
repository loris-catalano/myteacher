package it.unisannio.gruppo3.payment.Presentation;

import it.unisannio.gruppo3.entities.Payment;
import it.unisannio.gruppo3.payment.logic.PaymentLogic;
import it.unisannio.gruppo3.payment.logic.PaymentLogicImpl;
import jakarta.ws.rs.*;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.*;

import java.net.URI;
import java.util.ArrayList;

@Path("/payments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PaymentService {

    private PaymentLogic logic;

    public PaymentService() {
        logic = new PaymentLogicImpl();
    }

    @POST
    public Response createPayment(Payment payment) {
        Long id = logic.createPayment(payment);
        if(id != null) {
            URI uri = URI.create("/payments/" + id);
            return Response.created(uri).build();
        }
        return Response.serverError().build();
    }

    @GET
    @Path("/{id}")
    public Response getPayment(@PathParam("id") Long id) {
        Payment payment = logic.getPayment(id);
        if(payment != null) {
            return Response.ok(payment).build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    @GET
    public Response getAllPayments() {
        ArrayList<Payment> payments = logic.getAllPayments();
        return Response.ok(payments).build();
    }
}
