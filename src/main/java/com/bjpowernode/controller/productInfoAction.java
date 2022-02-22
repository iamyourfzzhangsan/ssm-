package com.bjpowernode.controller;

import com.bjpowernode.pojo.ProductInfo;
import com.bjpowernode.service.ProductInfoService;
import com.bjpowernode.utils.FileNameUtil;
import com.github.pagehelper.PageInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/prod")
public class productInfoAction {
    String saveFileName = "";
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

//    异步Ajax文件上传
    @ResponseBody
    @RequestMapping("/ajaxImg")
    public Object ajaxImg(MultipartFile pimage,HttpServletRequest request){
//        提取生成文件名UUID+上传图片的后缀。JPG .PNG
        saveFileName = FileNameUtil.getUUIDFileName()+FileNameUtil.getFileType(pimage.getOriginalFilename());
        String path = request.getServletContext().getRealPath("/image_big");
//        转存
        try {
            pimage.transferTo(new File(path+File.separator+saveFileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
//        返回客户端json对象，封装图片的路径，为了在页面实现立即回显
        JSONObject object = new JSONObject();
        object.put("imgurl",saveFileName);

        return object.toString();
    }

    @RequestMapping("/save")
    public String save(ProductInfo info,HttpServletRequest request){
        info.setpImage(saveFileName);
        info.setpDate(new Date());

        int num = -1;
        try {
            num = productInfoService.save(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","增加成功");
        }else {
            request.setAttribute("msg","增加失败");
        }
//        清空saveFileName
        saveFileName = "";
//        页面跳转
        return "forward:/prod/split.action";
    }

    @RequestMapping("/one")
    public String one(int pid, Model model){
        ProductInfo info = productInfoService.getByID(pid);
        model.addAttribute("prod",info);
        return "update";
    }

    @RequestMapping("/update")
    public String update(ProductInfo info,HttpServletRequest request){

        if (!saveFileName.equals("")){
            info.setpImage(saveFileName);
        }
        int num = -1;
        try {
            num = productInfoService.update(info);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","更新成功");
        }else {
            request.setAttribute("msg","更新失败");
        }
        saveFileName="";
        return "forward:/prod/split.action";
    }

    @RequestMapping("/delete")
    public  String delete(int pid,HttpServletRequest request){
        int num = -1;
        try {
            num = productInfoService.delete(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (num>0){
            request.setAttribute("msg","删除成功");
        }else {
            request.setAttribute("msg","删除失败");

        }
        return "forward:/prod/deleteAjaxSplit.action";
    }
    @ResponseBody
    @RequestMapping(value = "/deleteAjaxSplit",produces = "text/html;charset=UTF-8")
    public Object deleteAjaxSplit(HttpServletRequest request){
        PageInfo info = productInfoService.splitPage(1,PAGE_SIZE);
        request.getSession().setAttribute("info",info);
        return request.getAttribute("msg");
    }
//    批量删除商品
    @RequestMapping("/deleteBatch")
    public String deleteBatch(String pids,HttpServletRequest request){
//        将上传上来的字符串截开，形成商品ID的字符数组
        String []ps = pids.split(",");
        int num = productInfoService.deleteBeach(ps);
        try {
            if (num>0){
                request.setAttribute("msg","批量删除");
            }else {
                request.setAttribute("msg","批量删除失败");
            }
        } catch (Exception e) {
            request.setAttribute("msg","商品不可删除");
        }
        return "forward:/prod/deleteAjaxSplit.action";
    }
}
