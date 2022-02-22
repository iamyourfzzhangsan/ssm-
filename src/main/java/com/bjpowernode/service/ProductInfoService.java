package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    //    显示全部商品，不分页
    List<ProductInfo> getAll();
//    分页功能实现
    PageInfo splitPage(int pageNum,int pageSize);
//商品添加
    int save (ProductInfo info);
//    按主键查询商品ID
    ProductInfo getByID(int pid);
//    更新商品
    int update(ProductInfo info );
//    单个商品的删除
    int delete(int pid);
//    批量删除
    int deleteBeach(String []ids);



}
