package models;

import android.arch.persistence.room.PrimaryKey;

public class Comment {

    @PrimaryKey(autoGenerate = true)
    private  int id;

    String user_id;
    String comment_id;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    private String comment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Comment(String user_id, String comment_id, String comment) {
        this.user_id = user_id;
        this.comment_id = comment_id;
        this.comment = comment;
    }
}
