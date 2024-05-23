package com.aas.controller;

import com.aas.config.jwt.Logined;
import com.aas.config.jwt.NotLogined;
import com.aas.controller.base.Result;
import com.aas.entity.SysUser;
import com.aas.service.EmailService;
import com.aas.service.MessageService;
import com.aas.service.SysUserService;
import com.aas.service.thread.RunnableDemo;
import com.aas.util.Excel.EasyExcelUtil;
import com.aas.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/demo")
@NotLogined
public class DemoController {

    @Autowired
    SysUserService sysUserService;

    @Autowired
    EmailService emailService;

    @Autowired
    MessageService messageService;

    @Value("${doc.folder}")
    String docFolder;

    @RequestMapping("/UserList")
    public Result getSysUserList(){
        return Result.success(sysUserService.getSysUserByParameter("admin"));
    }

    @RequestMapping("/UserListByParameter")
    public Result UserListByParameter(@RequestParam( name = "id")String id){
        return Result.success(sysUserService.getSysUserByParameter(id));
    }

    @RequestMapping("/error")
    public Result error(){
         int errorNum = 10/0;
         System.out.println("111111111111111111111111111111");
        return Result.errro(201,"错误");
    }

    /**
     * 推送邮件
     * @return
     */
    @RequestMapping("/sendEmail")
    public Result sendEmail(){
        String to = "a984958991@126.com";
        String title = "这是一封很牛逼的邮件";
        String content = "这是内容";
        String htmlContent = "<h1>测试内容H1</h1><hr><h2>测试内容H2</h2>";
        //普通邮件
//        emailService.sendSimpleMailMessge(to,title,content);
        //HTML邮件
//        emailService.sendMimeMessge(to,title,htmlContent);
        //发送带有附件的email
        emailService.sendMimeMessge(to,title,htmlContent,"G://temp/testExcel.xlsx");
        return Result.success();
    }

    /**
     * 写入Excel
     * @return
     */
    @RequestMapping("/createExcel")
    public Result createExcel(){
        List<SysUser> list = sysUserService.getSysUserByParameter("admin");

        String fileName = "/testCreat.xlsx";
        //声明表头，如果表头的List<String>重复会合并单元格
        List<List<String>> head =  new ArrayList<>();
        head.add(Arrays.asList("Id"));
        head.add(Arrays.asList("email"));
        head.add(Arrays.asList("userName"));
        head.add(Arrays.asList("phoneNum"));
        //data添加数据
        List<List<Object>> data = new ArrayList<>();
        for (SysUser user: list) {
            List<Object> datalist = new ArrayList<>();;
            datalist.add(user.getUserId());
            datalist.add(user.getEmail());
            datalist.add(user.getUserName());
            datalist.add(user.getPhone());
            data.add(datalist);
        }
        //单个sheet
//        EasyExcelUtil.write(docFolder+fileName,head,data);
        //多个sheet
        List<List<List<String>>> heads = new ArrayList<>();
        heads.add(head);
        heads.add(head);
        List<List<List<Object>>> datas = new ArrayList<>();
        datas.add(data);
        datas.add(data);
        //声明sheet
        List<String> sheets = new ArrayList<>();
        sheets.add("sheet1");
        sheets.add("sheet2");

        EasyExcelUtil.write(docFolder+fileName,heads,datas,sheets);
        return Result.success();
    }

    /**
     * 验证token拦截器
     * @return
     */
    @RequestMapping("/verify")
    @Logined
    public Result verify(){
        return Result.success("验证通过!");
    }

    /**
     * 获取token，验证非拦截
     * @param userId 唯一ID
     * @return
     */
    @RequestMapping("/getToken")
    @NotLogined
    public Result getToken(@RequestParam( name = "userId")String userId){
        String tokenString = JwtUtils.sign(userId,userId);
        return Result.success(tokenString);
    }

    /**
     * 推送消息
     * @param tousers 推送人员
     * @param content 消息内容
     * @return
     */
    @RequestMapping("/sendMessage")
    @NotLogined
    public Result sendMessage(@RequestParam( name = "tousers")String tousers,
                              @RequestParam( name = "content")String content){
        //推送普通消息
//        return messageService.sendMassage(tousers,content);
        //推送图片消息
        String path = "G://temp//IMG_0430.JPG";
        return messageService.sendImgMessage(tousers,path);
    }

    /**
     * 多线程查询线程等待
     * @return
     * @throws InterruptedException
     */
    @RequestMapping("/threadQuery")
    @NotLogined
    public Result threadQuery() throws InterruptedException {
        RunnableDemo runnableDemo = new RunnableDemo("test1",sysUserService.getSysUserDao());
        RunnableDemo runnableDemo1 = new RunnableDemo("test1",sysUserService.getSysUserDao());
        RunnableDemo runnableDemo2 = new RunnableDemo("test1",sysUserService.getSysUserDao());
        RunnableDemo runnableDemo3 = new RunnableDemo("test1",sysUserService.getSysUserDao());

        Thread thread = new Thread(runnableDemo);
        Thread thread1 = new Thread(runnableDemo1);
        Thread thread2 = new Thread(runnableDemo2);
        Thread thread3 = new Thread(runnableDemo3);

        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();

        thread.join();
        thread1.join();
        thread2.join();
        thread3.join();

        List<SysUser> list = new ArrayList<>();
        list.add(runnableDemo.getSysUser());
        list.add(runnableDemo1.getSysUser());
        list.add(runnableDemo2.getSysUser());
        list.add(runnableDemo3.getSysUser());

        return Result.success(list);
    }


}
