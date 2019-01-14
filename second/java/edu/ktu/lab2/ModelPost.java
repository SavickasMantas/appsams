package edu.ktu.lab2;

public class ModelPost {

    int id;
    int userId;
    String title;
    String bodyText;

    public ModelPost(){}

    public ModelPost(int id, int userId, String title, String bodyText){
        this.id = id;
        this.userId = userId;
        this.title = title;
        this.bodyText = bodyText;
    }

    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getUserId(){
        return this.userId;
    }
    public void setUserId(int userId){
        this.userId = userId;
    }
    public String getTitle(){
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBodyText() {
        return this.bodyText;
    }
    public void setBodyText(String bodyText) {
        this.bodyText = bodyText;
    }
}
