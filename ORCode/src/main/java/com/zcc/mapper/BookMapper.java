package com.zcc.mapper;


import com.zcc.entity.Book;
import com.zcc.utils.TkMyBatisMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookMapper extends TkMyBatisMapper<Book> {

    List<Book> selectTest(@Param("sql") String sql);
}
