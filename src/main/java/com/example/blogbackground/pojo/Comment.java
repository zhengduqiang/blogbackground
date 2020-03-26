package com.example.blogbackground.pojo;

import java.util.Date;

/**
 * 评论表
 */
public class Comment {
    private Integer cId;
    private String board_titm;
    private String board_name;
    private Date board_time;
    private Integer userName_Id;
    private Integer articledetailsId;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public String getBoard_titm() {
        return board_titm;
    }

    public void setBoard_titm(String board_titm) {
        this.board_titm = board_titm;
    }

    public String getBoard_name() {
        return board_name;
    }

    public void setBoard_name(String board_name) {
        this.board_name = board_name;
    }

    public Date getBoard_time() {
        return board_time;
    }

    public void setBoard_time(Date board_time) {
        this.board_time = board_time;
    }

    public Integer getUserName_Id() {
        return userName_Id;
    }

    public void setUserName_Id(Integer userName_Id) {
        this.userName_Id = userName_Id;
    }

    public Integer getArticledetailsId() {
        return articledetailsId;
    }

    public void setArticledetailsId(Integer articledetailsId) {
        this.articledetailsId = articledetailsId;
    }
}
