package com.study.spring.controller;

import com.study.spring.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tp on 2018/12/27.
 */
@Controller
public class UserController {

    @RequestMapping(value = "/hello" ,method = RequestMethod.GET)
    public String hello(){
        System.out.println("hello");
        return "ok";
    }
    @RequestMapping(value = "/unAuth" ,method = RequestMethod.GET)
    public String unAuth(){
        return "unAuth";
    }

    @RequestMapping(value = "/add")
    public String add(){
        return "user/add";
    }

    @RequestMapping(value = "/testThemleaf")
    public String testThemleaf(Model model){
        model.addAttribute("name" ,"哈哈，操蛋的人生");
        return "test";
    }
    @RequestMapping(value = "/login")
    public String login(@RequestParam(name = "uname" , required = true) String uname , @RequestParam(name = "pwd" ,required = true) String pwd , Model model){
        // 拿到subject
        Subject subject = SecurityUtils.getSubject();
        // 封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(uname , pwd);
        //执行登录方法
        try {
            subject.login(token);
            // 登陆成功
            return "redirect:/testThemleaf";
        }catch (UnknownAccountException e){
            model.addAttribute("msg" ,"用户名不存在");
            return "login";
        }
        catch (IncorrectCredentialsException e){
            model.addAttribute("msg" ,"密码错误");
            return "login";
        }
    }
}
