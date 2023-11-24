package com.hendisantika.paymentservice.repository;

import com.hendisantika.paymentservice.entity.Payment;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-driven
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/23
 * Time: 06:20
 * To change this template use File | Settings | File Templates.
 */
public interface PaymentRepository extends CrudRepository<Payment, Long> {
}
