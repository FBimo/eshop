package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaymentRepository {
    Map<String, Payment> payments = new HashMap<>();
    public Payment save(Payment payment){
        payments.put(payment.getId(), payment);
        return payment;
    }

    public Payment findById(String id){
        return payments.get(id);
    }

    public List<Payment> findAll(){
        return new ArrayList<>(payments.values());
    }
}
