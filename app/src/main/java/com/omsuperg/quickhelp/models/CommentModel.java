package com.omsuperg.quickhelp.models;

/**
 * Created by Edgar on 10/7/2017.
 */

public class CommentModel {
    private int rating;
    private String comment;
    private String userId;

    public static CommentModel getRandom() {
        CommentModel commentModel = new CommentModel();
        commentModel.setRating((int) (Math.random() * 5));
        commentModel.setUserId("" + (long) (Math.random() * 472567553116840L));
        commentModel.setComment(commentModel.getRating() > 2 ? "Excelente servicio, gracias." : "Mucho que desear");
        return commentModel;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
