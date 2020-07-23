package com.liuxi.controller;


import com.liuxi.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class IndexController {

    @RequestMapping("index")
    public String index(){
        System.out.println(666);
        return "index";
    }

//    @RequestMapping("login")
//    public String login(){
//        System.out.println(666);
//        return "index";
//    }

    @RequestMapping("login")
    public String login(String user, String pwd, ModelMap map, HttpSession session) throws Exception {
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user, pwd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(usernamePasswordToken); // 完成登录
            User user2 = (User) subject.getPrincipal();
            session.setAttribute("user", user2);
            return "redirect:/";
        } catch (Exception e) {
            return "login";// 返回登录页面
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session) {
        //先remove session作用域的用户信息
        session.removeAttribute("user");
        //再shiro框架做logout操作
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/toLogin";
    }

    @RequestMapping("toLogin")
    public String toLogin(){
        System.out.println(666);
        return "login";
    }

    @RequiresPermissions("/insert")      //	@RequiresPermissions("添加权限的url")使用来添加权限的
    @RequestMapping("insert")
    public String insert(){
        return "/user/insert";
    }

    @RequiresPermissions("/update")
    @RequestMapping("update")
    public String update(){
        return "/user/update";
    }

    @RequiresPermissions("/select")
    @RequestMapping("select")
    public String select(){
        return "/user/select";
    }

    @RequiresPermissions("/delete")
    @RequestMapping("delete")
    public String delete(){
        return "/user/delete";
    }
}
