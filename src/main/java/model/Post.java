package model;

import java.util.Date;

public class Post {
    private String idPost;
    private String useName;
    private String imgSrc;
    private Date time;

    public Post(String idPost, String useName, String imgSrc, Date time) {
        this.idPost = idPost;
        this.useName = useName;
        this.imgSrc = imgSrc;
        this.time = time;
    }

    public String getIdPost() {
        return idPost;
    }

    public void setIdPost(String idPost) {
        this.idPost = idPost;
    }

    public String getUseName() {
        return useName;
    }

    public void setUseName(String useName) {
        this.useName = useName;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
