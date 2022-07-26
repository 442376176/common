package com.zcc.utils;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

public interface TkMyBatisMapper<T> extends Mapper<T>, MySqlMapper<T> {

}