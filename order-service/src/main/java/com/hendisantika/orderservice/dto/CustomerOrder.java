package com.hendisantika.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-driven
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/23
 * Time: 06:11
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class CustomerOrder {
    private String item;

    private int quantity;

    private double amount;

    private String paymentMode;

    private long orderId;

    private String address;
}
