package it.unisannio.gruppo3.payment.logic;

import it.unisannio.gruppo3.entities.Payment;
import it.unisannio.gruppo3.payment.Persistence.PaymentDao;
import it.unisannio.gruppo3.payment.Persistence.PaymentDaoMongo;

import java.util.ArrayList;

public class PaymentLogicImpl implements PaymentLogic {

    private PaymentDao paymentDAO;

    public PaymentLogicImpl(){
        paymentDAO = new PaymentDaoMongo();
    }

    @Override
    public Long createPayment(Payment payment) {
        return paymentDAO.createPayment(payment);
    }

    @Override
    public Payment getPayment(Long id) {
        return paymentDAO.getPayment(id);
    }



    @Override
    public boolean deletePayment(Long id) {
        return paymentDAO.deletePayment(id);
    }

    @Override
    public ArrayList<Payment> getAllPayments() {
        return paymentDAO.getAllPayments();
    }}
