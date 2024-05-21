package me.taciozturk;

import java.util.Date;

public class Post {
    private String message;
    private int ownerID;
    private Date date;

    public Post(String message, int owner, Date date) {
        this.message = message;
        this.ownerID = owner;
        this.date = date;
    }
    public String getMessage() {
        return message;
    }
    public int getOwner() {
        return ownerID;
    }
    public Date getDate() {
        return date;
    }


}
