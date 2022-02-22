package com.bjpowernode.service.impl;

import com.bjpowernode.mapper.ProductInfoMapper;
import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.pojo.ProductInfoExample;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductInfoServiceImpl implements ProductInfoService {
//    切记业务逻辑层中有一定的数据访问层的对象
    @Autowired
    ProductInfoMapper productInfoMapper;

    @Override
    public int save(ProductInfo info) {
        return productInfoMapper.insert(info);
    }

    @Override
//    按主键查询商品ID
    public ProductInfo getByID(int pid) {
        return productInfoMapper.selectByPrimaryKey(pid);
    }

    @Override
    public int update(ProductInfo info) {
        return productInfoMapper.updateByPrimaryKey(info);
    }
//单个商品删除
    @Override
    public int delete(int pid) {
        return productInfoMapper.deleteByPrimaryKey(pid);

    }

    @Override
    public int deleteBeach(String[] ids) {
        return productInfoMapper.deleteBatch(ids);
    }

    @Override
    public List<ProductInfo> getAll() {
        return productInfoMapper.selectByExample(new ProductInfoExample());
    }

    @Override
    public PageInfo splitPage(int pageNum, int pageSize) {
//        分页插件使用pagehelper工具类完成
        PageHelper.startPage(pageNum,pageSize);
//        进行pageinfo的数据封装
//        进行有条件的查询操作，必须创建productInfoExample对象
        ProductInfoExample example = new ProductInfoExample();
        example.setOrderByClause("p_id desc");
//        设置完排序后，取集合，切记要先设置使用分页插件
        List<ProductInfo> list = productInfoMapper.selectByExample(example);
//        将查到的集合封装到插件工具pageinfo
        PageInfo<ProductInfo> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }
}
