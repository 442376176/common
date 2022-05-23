package com.zcc.springcloud.mapper;

import com.zcc.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author zcc
 * @version 1.0
 * @date 2022/2/11 11:28
 */
@Mapper
public interface PaymentMapper{
    int create(Payment param);
    List<Payment> getAll();
    Payment getById(@Param("id") Long id);
}
