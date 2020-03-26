package com.example.blogbackground.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AllController {
    /**
     * 跳转首页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "index";
    }
    /**
     * 跳转异常页面
     * @return
     */
    @RequestMapping("404")
    public String erre(){
        return "404";
    }
    /**
     * 跳转文章详情页面
     * @return
     */
    @RequestMapping("article")
    public String article(){
        return "article";
    }
    /**
     * 跳转注册页面
     * @return
     */
    @RequestMapping("register")
    public String register(){
        return "register";
    }
    /**
     * 跳转登录页面
     * @return
     */
    @RequestMapping("login")
    public String login(){
        return "login";
    }
    /**
     * 跳转关于我页面
     * @return
     */
    @RequestMapping("tags")
    public String tags(){
        return "tags";
    }
    /**
     * 跳转分类页面
     * @return
     */
    @RequestMapping("category")
    public String category(){
        return "category";
    }

    /**
     * 跳转友情链接页面
     * @return
     */
    @RequestMapping("links")
    public String links(){
        return "links";
    }
    /**
     * 跳转更新日记页面
     * @return
     */
    @RequestMapping("readers")
    public String readers(){
        return "readers";
    }


}
