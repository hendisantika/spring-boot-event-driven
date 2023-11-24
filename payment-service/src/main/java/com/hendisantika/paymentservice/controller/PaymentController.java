package com.hendisantika.paymentservice.controller;

import com.hendisantika.paymentservice.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

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
public class PaymentController {
    private final PaymentRepository paymentRepository;
}
