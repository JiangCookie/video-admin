package com.jyh.videoadmin.pojo;

import javax.persistence.*;

@Table(name = "users")
public class Users {
    @Id
    private Integer id;

    private String username;

    private String password;

    @Column(name = "face_image")
    private String faceImage;

    private String nickname;

    @Column(name = "fans_counts")
    private Integer fansCounts;

    @Column(name = "follow_counts")
    private Integer followCounts;

    @Column(name = "receive_like_counts")
    private Integer receiveLikeCounts;

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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username
     */
    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    /**
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * @return face_image
     */
    public String getFaceImage() {
        return faceImage;
    }

    /**
     * @param faceImage
     */
    public void setFaceImage(String faceImage) {
        this.faceImage = faceImage == null ? null : faceImage.trim();
    }

    /**
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname == null ? null : nickname.trim();
    }

    /**
     * @return fans_counts
     */
    public Integer getFansCounts() {
        return fansCounts;
    }

    /**
     * @param fansCounts
     */
    public void setFansCounts(Integer fansCounts) {
        this.fansCounts = fansCounts;
    }

    /**
     * @return follow_counts
     */
    public Integer getFollowCounts() {
        return followCounts;
    }

    /**
     * @param followCounts
     */
    public void setFollowCounts(Integer followCounts) {
        this.followCounts = followCounts;
    }

    /**
     * @return receive_like_counts
     */
    public Integer getReceiveLikeCounts() {
        return receiveLikeCounts;
    }

    /**
     * @param receiveLikeCounts
     */
    public void setReceiveLikeCounts(Integer receiveLikeCounts) {
        this.receiveLikeCounts = receiveLikeCounts;
    }
}