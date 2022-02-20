package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class productInfoAction {
    public static final int PAGE_SIZE = 5;
//    切记：界面层中，一定会有业务逻辑层的对象
    @Autowired
    ProductInfoService productInfoService;
//    显示商品全部不分页
    @RequestMapping("/getAll")
    public String getAll(HttpServletRequest request){
        List<ProductInfo> list = productInfoService.getAll();
        request.setAttribute("list",list);
        return "product";
    }
//    显示第一页的五条记录
    @RequestMapping("/split")
    public String split(HttpServletRequest request){
//        得到第一页的数据
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.setAttribute("info",info);
        return "product";
    }

//    Ajax分页翻页处理
    @ResponseBody
    @RequestMapping("/ajaxsplit")
    public void ajaxSplit(int page, HttpSession session){
//        取得当前page参数的页面的数据
        PageInfo info = productInfoService.splitPage(page,PAGE_SIZE);
        session.setAttribute("info",info);
    }

}
