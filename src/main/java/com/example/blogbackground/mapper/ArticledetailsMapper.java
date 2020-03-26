package com.example.blogbackground.mapper;

import com.example.blogbackground.pojo.Articledetails;
import com.example.blogbackground.pojo.Comment;
import com.example.blogbackground.pojo.Everyday;
import com.example.blogbackground.pojo.Leave;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper//这个注解表示这个是mybaits的mapper类：Dao
@Repository
public interface ArticledetailsMapper {
    /**
     * 同步：每日一句
     *
     * @return
     */
    Everyday findEverydayAll();

    /**
     * 同步：查询热门文章
     *
     * @return
     */
    List<Articledetails> findArtickledetails();

    /**
     * 同步：按条件查询最新发布的文章
     *
     * @param articledetails
     * @return
     */
    List<Articledetails> findArtickledetailsDate(Articledetails articledetails);


    /**
     * 同步：今日推荐文章
     *
     * @return
     */
    Articledetails findArticledetailsTop();

    /**
     * 同步：按照文章分类查询
     *
     * @param articledetails
     * @return
     */
    List<Articledetails> findWZFL(Articledetails articledetails);

    /**
     * 同步：按照文章类型查询标签
     *
     * @param tId
     * @return
     */
    List<Articledetails> findTypeLabel(int tId);

    /**
     * 同步：根据标签查询文章
     *
     * @param lId
     * @return
     */
    List<Articledetails> findLaelWZ(int lId);

    /**
     * 同步：查询更新日记
     *
     * @return
     */
    List<Articledetails> findArticledetailsGXRJ();

    /**
     * 同步：查询文章详情
     *
     * @param aId
     * @return
     */
    Articledetails findArticledetailsXQ(int aId);

    /**
     * 异步：当点击文章阅读数+1
     *
     * @param aId
     * @return
     */
    int updateArticledetailsWatchNum(int aId);

    /**
     * 同步：相关推荐
     *
     * @param articledetails
     * @return
     */
    List<Articledetails> findArticledetailsXGTJ(Articledetails articledetails);

    /**
     * 同步：查询文章评论
     *
     * @param articledetailsId
     * @return
     */
    List<Comment> findCommentAll(int articledetailsId);

    /**
     * 异步：给文章评论
     *
     * @param comment
     * @return
     */
    int addComment(Comment comment);

    /**
     * 异步：给文章评论之后修改文章的评论数+1
     *
     * @param aId
     * @return
     */
    int updateArticledetailsCount(int aId);

    /**
     * 同步：查询所有留言
     *
     * @return
     */
    List<Leave> findLeaveAll();

    /**
     * 异步：增加留言
     *
     * @param leave
     * @return
     */
    int addLeave(Leave leave);
}
