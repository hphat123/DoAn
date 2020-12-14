package com.example.ungdungdocbao;

public class Item {
    private   String title;
    private  String description;
    private  String img;
    private  String  pubdate;
    private String link;
    public Item(){

    }
    public Item(String title,String description,String img,String pubdate,String link)
    {
      this.title =title;
      this.description = description;
      this.img = description ;
      this.pubdate = pubdate;
      this.link = link;
    }
    public String getTitle(){
      return title;
    }
    public void setTitle(String title){
       this.title= title;
    }
    public String getDescription(){
        return  description;
    }
    public void setDescription(String description){
       this.description = description;
    }
    public  String getImg(){
        return img;
    }
    public void setImg(String img){
     this.img=img;
    }
    public String getPubdate(){
        return  pubdate;
    }
    public  void  setPubdate(String pubdate){
        this.pubdate = pubdate;
    }
    public String getLink(){
        return  link;
    }
    public  void  setLink(String link){
    this.link = link;
    }
}
