package nucleus.tutget.service.payment.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import nucleus.tutget.service.payment.model.Payment;
import nucleus.tutget.service.payment.repository.PaymentRepository;

@Service
public class PaymentService {
     
    @Autowired
    private PaymentRepository paymentRepository;

    public String addPayment(Payment payment) {
        paymentRepository.save(payment);
        return payment.getTransactionId();
    }

    public Optional<Payment> getPayment(String transactionId) {
        return paymentRepository.findById(transactionId);
    }
}
