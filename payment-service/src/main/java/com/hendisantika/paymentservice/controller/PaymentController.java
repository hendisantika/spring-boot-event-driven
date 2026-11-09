package com.hendisantika.paymentservice.controller;

import com.hendisantika.paymentservice.dto.CustomerOrder;
import com.hendisantika.paymentservice.dto.OrderEvent;
import com.hendisantika.paymentservice.entity.Payment;
import com.hendisantika.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;
import tools.jackson.databind.json.JsonMapper;

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
@Controller
@RequiredArgsConstructor
@Slf4j
public class PaymentController {
    private final PaymentRepository paymentRepository;

    private final JsonMapper jsonMapper;

    @KafkaListener(topics = "orders-topic", groupId = "orders-group")
    public void processPayment(String event) {
        log.info("Received event for payment {}", event);
        OrderEvent orderEvent = jsonMapper.readValue(event, OrderEvent.class);

        CustomerOrder order = orderEvent.getOrder();

        Payment payment = new Payment();
        payment.setAmount(order.getAmount());
        payment.setMode(order.getPaymentMode());
        payment.setOrderId(order.getOrderId());
        payment.setStatus("SUCCESS");
        paymentRepository.save(payment);
    }
}
