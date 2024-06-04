package it.unisannio.gruppo3.payment.logic;


import it.unisannio.gruppo3.entities.Payment;
import java.util.ArrayList;

public interface PaymentLogic {

    Long createPayment(Payment payment);

    Payment getPayment(Long id);

    boolean deletePayment(Long id);

    ArrayList<Payment> getAllPayments();

}