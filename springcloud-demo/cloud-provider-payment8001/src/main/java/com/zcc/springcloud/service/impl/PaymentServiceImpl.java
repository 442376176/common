package com.zcc.springcloud.service.impl;

import com.zcc.springcloud.entities.Payment;
import com.zcc.springcloud.mapper.PaymentMapper;
import com.zcc.springcloud.service.PaymentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:47
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    // java自带注解
    @Resource
    PaymentMapper paymentMapper;

    @Override
    public Payment getById(Long id) {
        Payment byId = paymentMapper.getById(id);
        return byId;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public int insert(Payment payment) {
        int i = paymentMapper.create(payment);
        return i;
    }

    @Override
    public List<Payment> getAll() {
        List<Payment> all = paymentMapper.getAll();
        return all;
    }
}
