package com.neusoft.xlm.entity;

import java.io.Serializable;
import java.util.List;

/**
 * (Movies)实体类
 *
 * @author makejava
 * @since 2023-03-22 16:24:43
 */
public class Movies implements Serializable {
    private static final long serialVersionUID = 587701505670033372L;

    private Integer id;

    private String directors;

    private String rate;

    private String title;

    private String casts;

    private String cover;

    private String year;

    private String types;

    private String country;

    private String lang;

    private String time;

    private String movieTime;

    private String comment_len;

    private String starts;

    private String summary;

    private String comments;

    private String imglist;

    private String movieurl;

    private String detaillink;

    private List<String> imageList;

    public List<String> getImageList() {
        return imageList;
    }

    public void setImageList(List<String> imageList) {
        this.imageList = imageList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDirectors() {
        return directors;
    }

    public void setDirectors(String directors) {
        this.directors = directors;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCasts() {
        return casts;
    }

    public void setCasts(String casts) {
        this.casts = casts;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getmovieTime() {
        return movieTime;
    }

    public void setmovieTime(String movieTime) {
        this.movieTime = movieTime;
    }

    public String getComment_len() {
        return comment_len;
    }

    public void setComment_len(String comment_len) {
        this.comment_len = comment_len;
    }

    public String getStarts() {
        return starts;
    }

    public void setStarts(String starts) {
        this.starts = starts;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getImglist() {
        return imglist;
    }

    public void setImglist(String imglist) {
        this.imglist = imglist;
    }

    public String getMovieurl() {
        return movieurl;
    }

    public void setMovieurl(String movieurl) {
        this.movieurl = movieurl;
    }

    public String getDetaillink() {
        return detaillink;
    }

    public void setDetaillink(String detaillink) {
        this.detaillink = detaillink;
    }

}

