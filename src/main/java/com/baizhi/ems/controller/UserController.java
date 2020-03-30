package com.baizhi.ems.controller;

import com.baizhi.ems.entity.User;
import com.baizhi.ems.service.UserService;
import com.baizhi.ems.utils.ValidateImageCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("user")
public class UserController {


    @Autowired
    private UserService userService;


    //开发用户登录
    @RequestMapping("login")
    public String login(String username,String password,HttpSession session){
        User user = userService.login(username, password);
        //判断  user 为空  用户户名密码错误      //不为空  登录成功
        if(user!=null){
            session.setAttribute("user",user);
            return "redirect:/emp/findAll";//查询员工的所有
        }else{
            return "redirect:/ems/login.jsp";
        }

    }


    //开发用户注册
    @PostMapping("regist")
    public String regist(User user,String code,HttpSession session){
        System.out.println("user = " + user);
        //1.判断验证码是否通过
        if(session.getAttribute("code").toString().equalsIgnoreCase(code)){//2.通过之后注册
            userService.save(user);
            return "redirect:/ems/login.jsp";
        }else{//3.验证码不通过 直接回到注册页面
            return "redirect:/ems/regist.jsp";
        }
    }

    //生成验证码
    @GetMapping("getImage")
    public void getImage(HttpSession sesssion, HttpServletResponse response) throws IOException {
        //生成验证码
        String securityCode = ValidateImageCodeUtils.getSecurityCode();
        //将验证码方式session
        sesssion.setAttribute("code",securityCode);
        //生成图片
        BufferedImage image = ValidateImageCodeUtils.createImage(securityCode);
        //输出图片  通过响应方式输出
        ServletOutputStream os = response.getOutputStream();
        //调用工具类
        ImageIO.write(image,"png",os);
    }
}
