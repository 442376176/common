//package com.eazytec.myMapperTest;
//
//import com.eazytec.common.TkMyBatisMapper;
//import com.eazytec.mapper.MapperBeanMapper;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import org.apache.ibatis.session.SqlSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import tk.mybatis.mapper.entity.Example;
//
//import java.util.List;
//
///**
// * @作者 朱志鹏
// * @时间 2021-07-07 下午4:10
// */
//@Service
//public class MapperUtils {
//
//    @Autowired
//    private  SqlSession sqlSession;
//
//    public  PageInfo getPageInfo(MapperBean mapperBean,Class aClass) {
//        MapperBeanMapper mapper = sqlSession.getMapper(MapperBeanMapper.class);
//        PageHelper.startPage(1,10);
//        Example example = new Example(aClass);
//        example.setTableName("t_tenant");
//        example.selectProperties("tenantId");
//        List<MapperBean> mapperBeans = mapper.selectByExample(example);
//
//        return new PageInfo<>(mapperBeans);
//
//    }
//}
