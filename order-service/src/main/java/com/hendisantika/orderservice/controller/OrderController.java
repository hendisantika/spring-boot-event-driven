package com.hendisantika.orderservice.controller;

import com.hendisantika.orderservice.dto.CustomerOrder;
import com.hendisantika.orderservice.dto.OrderEvent;
import com.hendisantika.orderservice.entity.Order;
import com.hendisantika.orderservice.entity.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-driven
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/23
 * Time: 06:13
 * To change this template use File | Settings | File Templates.
 */
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderRepository orderRepository;

    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    @PostMapping("/orders")
    public void createOrder(@RequestBody CustomerOrder customerOrder) {
        Order order = new Order();
        order.setAmount(customerOrder.getAmount());
        order.setItem(customerOrder.getItem());
        order.setQuantity(customerOrder.getQuantity());
        order.setStatus("CREATED");
        order = orderRepository.save(order);

        customerOrder.setOrderId(order.getId());

        OrderEvent orderEvent = new OrderEvent();
        orderEvent.setOrder(customerOrder);
        orderEvent.setType("ORDER_CREATED");

        kafkaTemplate.send("orders-topic", orderEvent);
    }
}
