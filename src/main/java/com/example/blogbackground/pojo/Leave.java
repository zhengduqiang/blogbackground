package com.example.blogbackground.pojo;

import java.util.Date;

/**
 * 留言表
 */
public class Leave {
    private Integer leave_Id;
    private String leave_name;
    private String leave_content;
    private Date leave_Date;
    private Integer user_Id;

    public String getLeave_name() {
        return leave_name;
    }

    public void setLeave_name(String leave_name) {
        this.leave_name = leave_name;
    }

    public String getLeave_content() {
        return leave_content;
    }

    public void setLeave_content(String leave_content) {
        this.leave_content = leave_content;
    }

    public Date getLeave_Date() {
        return leave_Date;
    }

    public void setLeave_Date(Date leave_Date) {
        this.leave_Date = leave_Date;
    }

    public Integer getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(Integer user_Id) {
        this.user_Id = user_Id;
    }

    public Integer getLeave_Id() {
        return leave_Id;
    }

    public void setLeave_Id(Integer leave_Id) {
        this.leave_Id = leave_Id;
    }
}
