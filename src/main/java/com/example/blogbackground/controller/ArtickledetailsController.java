package com.example.blogbackground.controller;

import com.example.blogbackground.mapper.ArticledetailsMapper;
import com.example.blogbackground.pojo.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class ArtickledetailsController {
    @Autowired
    private ArticledetailsMapper mapper;

    /**
     * 查询首页所有信息
     *
     * @param model
     * @param articledetails
     * @return
     */
    @RequestMapping("/findArticledetailsAll")
    public String findArticledetailsAll(Model model, Articledetails articledetails, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //同步：查询热门文章
        List<Articledetails> artickledetails = mapper.findArtickledetails();
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 20);
        //同步：按条件查询最新发布的文章
        List<Articledetails> artickledetailsDate = mapper.findArtickledetailsDate(articledetails);
        //同步：今日推荐文章
        Articledetails articledetailsTop = mapper.findArticledetailsTop();
        //同步：每日一句
        Everyday everydayAll = mapper.findEverydayAll();
        PageInfo<Articledetails> pageInfo = new PageInfo<Articledetails>(artickledetailsDate);
        //需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("artickledetails", artickledetails);//存储热门文章
        model.addAttribute("artickledetailsDates", artickledetailsDate);//存储所有的文章
        model.addAttribute("articledetailsTops", articledetailsTop);//存储今日推荐文章
        model.addAttribute("everydayAlls", everydayAll);//存储每日一句
        model.addAttribute("detailsTitle", articledetails.getDetailsTitle());
        return "index";
    }

    /**
     * 按照文章分类查询
     *
     * @param model
     * @param articledetails
     * @param pageNum
     * @return
     */
    @RequestMapping("/findArticledetailsType")
    public String findArticledetailsType(Model model, Articledetails articledetails, @RequestParam(defaultValue = "1", value = "pageNum") Integer pageNum) {
        //pageNum为下标位置，后者为页面容量
        PageHelper.startPage(pageNum, 10);
        List<Articledetails> wzfl = mapper.findWZFL(articledetails);
        List<Articledetails> typeLabel = mapper.findTypeLabel(articledetails.getType_id());
        //同步：每日一句
        Everyday everydayAll = mapper.findEverydayAll();
        //同步：查询热门文章
        List<Articledetails> artickledetails = mapper.findArtickledetails();
        PageInfo<Articledetails> pageInfo = new PageInfo<Articledetails>(wzfl);
        //需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("wzfls", wzfl);//存储文章
        model.addAttribute("typeLabels", typeLabel);//存储文章类型
        model.addAttribute("everydayAlls", everydayAll);//存储每日一句
        model.addAttribute("artickledetails", artickledetails);//存储热门文章
        model.addAttribute("detailsTitle", articledetails.getDetailsTitle());
        model.addAttribute("type_id", articledetails.getType_id());
        model.addAttribute("tId", articledetails.gettId());
        model.addAttribute("label_id", articledetails.getLabel_id());
        return "category";
    }

    /**
     * 同步：查询更新日记
     *
     * @param model
     * @return
     */
    @RequestMapping("findArticledetailsGXRJ")
    public String findArticledetailsGXRJ(Model model) {
        List<Articledetails> articledetailsGXRJ = mapper.findArticledetailsGXRJ();
        model.addAttribute("articledetailsGXRJs", articledetailsGXRJ);
        return "readers";
    }


    /**
     * 查询文章详情所有的内容
     *
     * @return
     */
    @RequestMapping("findArticledetailsXQ")
    public String findArticledetailsXQ(Articledetails articledetails, Model model) {
        //同步：每日一句
        Everyday everydayAll = mapper.findEverydayAll();
        //同步：查询热门文章
        List<Articledetails> artickledetails = mapper.findArtickledetails();
        //按照Id查询文章详情
        Articledetails articledetailsXQ = mapper.findArticledetailsXQ(articledetails.getaId());
        //异步：当点击文章阅读数+1
        mapper.updateArticledetailsWatchNum(articledetails.getaId());
        //同步：相关推荐
        List<Articledetails> articledetailsXGTJ = mapper.findArticledetailsXGTJ(articledetails);
        model.addAttribute("everydayAlls", everydayAll);//存储每日一句
        model.addAttribute("artickledetails", artickledetails);//存储热门文章
        model.addAttribute("articledetailsXQs", articledetailsXQ);//存储文章详情内容
        model.addAttribute("articledetailsXGTJs", articledetailsXGTJ);//存储相关推荐的信息
        // 同步：查询文章评论
        List<Comment> commentAll = mapper.findCommentAll(articledetails.getaId());
        //pageNum为下标位置，后者为页面容量
        PageInfo<Comment> pageInfo = new PageInfo<Comment>(commentAll);
        //需要把Page包装成PageInfo对象才能序列化。该插件也默认实现了一个PageInfo
        model.addAttribute("pageInfo", pageInfo);
        model.addAttribute("commentAlls", commentAll);//存储所有文章评论
        return "article";
    }

    /**
     * 异步：添加一条评论
     *
     * @param comment
     * @return
     */
    @ResponseBody
    @RequestMapping("addComment")
    public int addComment(HttpSession session, Comment comment, Model model) {
        Date date = new Date();
        comment.setBoard_time(date);
        User userMapper = (User) session.getAttribute("user");
        comment.setUserName_Id(userMapper.getuId());
        comment.setBoard_name(userMapper.getUserName());
        int i = mapper.addComment(comment);
        if (i == 1) {
            int i1 = mapper.updateArticledetailsCount(comment.getArticledetailsId());
            return 1;
        } else {
            return 0;
        }
    }

    /**
     * 同步：查询所有留言
     *
     * @param model
     * @return
     */
    @RequestMapping("findLeaveAll")
    public String findLeaveAll(Model model) {
        List<Leave> leaveAll = mapper.findLeaveAll();
        model.addAttribute("leaveAlls", leaveAll);
        return "tags";
    }

    /**
     *  异步：添加一条留言
     *
     * @param
     * @return
     */
    @ResponseBody
    @RequestMapping("addLeave")
    public int addLeave(HttpSession session, Leave leave) {
        Date date = new Date();
        leave.setLeave_Date(date);
        User userMapper = (User) session.getAttribute("user");
        leave.setLeave_name(userMapper.getUserName());
        leave.setUser_Id(userMapper.getuId());
        int i = mapper.addLeave(leave);
        if (i == 1) {
            return 1;
        } else {
            return 0;
        }
    }

}
