package it.unisannio.gruppo3.payment.Persistence;

import it.unisannio.gruppo3.entities.Payment;

import java.util.ArrayList;

public interface PaymentDao {
    String DATABASE_NAME = "myteacher";
    String COLLECTION_PAYMENTS = "Payments";


    String ELEMENT_ID= "_id";
    String ELEMENT_AMOUNT = "amount";
    String ELEMENT_STUDENTID = "studentId";
    String ELEMENT_TEACHERID = "teacherId";

    String ELEMENT_HIGHEST_ID = "highest";

    boolean dropDB();

    //boolean createDB();

    Long createPayment(Payment payment);

    Payment getPayment(Long id);

    boolean deletePayment(Long id);

    boolean closeConnection();

    ArrayList<Payment> getAllPayments();
}
