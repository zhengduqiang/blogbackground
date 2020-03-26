package com.example.blogbackground.controller;

import com.example.blogbackground.mapper.UserMapper;
import com.example.blogbackground.pojo.User;
import com.sun.xml.internal.ws.spi.db.DatabindingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class UserController {

    @Autowired
    private UserMapper mapper;

    /**
     * 异步：注册用户
     *
     * @param user
     * @param model
     * @return
     */
    @ResponseBody
    @RequestMapping("/addUser")
    public int addUser(User user, Model model) {
        Date date = new Date();
        user.setRegistration(date);
        user.setJurisdiction(0);
        int i = mapper.addUser(user);
        return i;
    }

    @RequestMapping("findUserLogin")
    public String findUserLogin(User user, HttpSession session, Model model) {
        User userLogin = mapper.findUserLogin(user);
        if (userLogin == null) {
            //登录失败！存放错误信息
            model.addAttribute("ts", "登录失败!");
            return "login";
        } else {
            session.setAttribute("user", userLogin);
            return "forward:/findArticledetailsAll";
        }
    }


}
