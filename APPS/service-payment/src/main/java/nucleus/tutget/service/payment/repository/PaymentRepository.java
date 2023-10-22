package nucleus.tutget.service.payment.repository;

import org.springframework.data.repository.CrudRepository;
import nucleus.tutget.service.payment.model.Payment;

public interface PaymentRepository extends CrudRepository<Payment, String> {

}