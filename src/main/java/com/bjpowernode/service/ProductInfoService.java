package com.bjpowernode.service;

import com.bjpowernode.pojo.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface ProductInfoService {

    //    显示全部商品，不分页
    List<ProductInfo> getAll();
//    分页功能实现
    PageInfo splitPage(int pageNum,int pageSize);

    int save (ProductInfo info);

}
