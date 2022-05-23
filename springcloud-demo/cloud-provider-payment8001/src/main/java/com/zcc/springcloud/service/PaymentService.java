package com.zcc.springcloud.service;

import com.zcc.springcloud.entities.Payment;

import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:46
 */
public interface PaymentService {

    Payment getById(Long id);

    int insert(Payment payment);

    List<Payment> getAll();
}
