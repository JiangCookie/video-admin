package com.jyh.videoadmin.pojo;

import java.util.Date;
import javax.persistence.*;

@Table(name = "users_report")
public class UsersReport {
    private Integer id;

    @Column(name = "deal_user_id")
    private String dealUserId;

    @Column(name = "deal_video_id")
    private String dealVideoId;

    private String title;

    private String content;

    private String userid;

    @Column(name = "create_date")
    private Date createDate;

    /**
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return deal_user_id
     */
    public String getDealUserId() {
        return dealUserId;
    }

    /**
     * @param dealUserId
     */
    public void setDealUserId(String dealUserId) {
        this.dealUserId = dealUserId == null ? null : dealUserId.trim();
    }

    /**
     * @return deal_video_id
     */
    public String getDealVideoId() {
        return dealVideoId;
    }

    /**
     * @param dealVideoId
     */
    public void setDealVideoId(String dealVideoId) {
        this.dealVideoId = dealVideoId == null ? null : dealVideoId.trim();
    }

    /**
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * @param content
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * @return userid
     */
    public String getUserid() {
        return userid;
    }

    /**
     * @param userid
     */
    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    /**
     * @return create_date
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate
     */
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}