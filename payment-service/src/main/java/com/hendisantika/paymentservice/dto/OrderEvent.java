package com.hendisantika.paymentservice.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by IntelliJ IDEA.
 * Project : spring-boot-event-driven
 * User: hendisantika
 * Email: hendisantika@gmail.com
 * Telegram : @hendisantika34
 * Date: 11/25/23
 * Time: 06:18
 * To change this template use File | Settings | File Templates.
 */
@Getter
@Setter
public class OrderEvent {

    private String type;

    private CustomerOrder order;
}
