package com.diaolizhi.wxloginpay.domain;


public class Video {

  private Integer id;
  private String videoTitle;
  private String videoCover;
  private Integer price;
  private String author;
  private String summary;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getVideoTitle() {
    return videoTitle;
  }

  public void setVideoTitle(String videoTitle) {
    this.videoTitle = videoTitle;
  }


  public String getVideoCover() {
    return videoCover;
  }

  public void setVideoCover(String videoCover) {
    this.videoCover = videoCover;
  }


  public Integer getPrice() {
    return price;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }


  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }


  public String getSummary() {
    return summary;
  }

  public void setSummary(String summary) {
    this.summary = summary;
  }

}
