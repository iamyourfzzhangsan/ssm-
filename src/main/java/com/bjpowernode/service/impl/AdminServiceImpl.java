package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.AdminMapper;
import com.bjpowernode.pojo.Admin;
import com.bjpowernode.pojo.AdminExample;
import com.bjpowernode.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AdminServiceImpl implements AdminService {
//    在业务逻辑层中，一定会有数据访问层的对象
    @Autowired
    AdminMapper adminMapper;
    @Override
    public Admin login(String name, String pwd) {
        AdminExample example = new AdminExample();
//        添查询条件
        example.createCriteria().andANameEqualTo(name);
//        执行查询操作返回list
        List<Admin> list =adminMapper.selectByExample(example);
        if (list.size()>0){

        }
        return null;
    }
}
