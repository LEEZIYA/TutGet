package com.tutget.tutgetmain.model.qna;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class QnaPost {
    private final String id;
    private String qnaString;
    private int upvotes;
    private int downvotes;
    private LocalDate postDate;
    private String posterId;
    private String posterName;
    private String acadLvl;
    private String acadSubj;

    private static final AtomicInteger idSeq = new AtomicInteger();

    public QnaPost() {
        this.id = String.valueOf(idSeq.incrementAndGet());
    }

    public QnaPost(String qnaString, String posterId, String posterName, String acadLvl, String acadSubj) {
        this.id = String.valueOf(idSeq.incrementAndGet());
        this.qnaString = qnaString;
        this.upvotes = 0;
        this.downvotes = 0;
        this.postDate = LocalDate.now();
        this.posterId = posterId;
        this.posterName = posterName;
        this.acadLvl = acadLvl;
        this.acadSubj = acadSubj;
    }

    public String getId() {
        return id;
    }

    public void setQnaString(String qnaString) {
        this.qnaString = qnaString;
    }

    public String getQnaString() {
        return qnaString;
    }

    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    public int getUpvotes() {
        return upvotes;
    }

    public void setDownvotes(int downvotes) {
        this.downvotes = downvotes;
    }

    public int getDownvotes() {
        return downvotes;
    }

    public LocalDate getPostDate() {
        return postDate;
    }

    public String getPosterId() {
        return posterId;
    }

    public String getPosterName() {
        return posterName;
    }

    public void setAcadLvl(String acadLvl) {
        this.acadLvl = acadLvl;
    }

    public String getAcadLvl() {
        return acadLvl;
    }

    public void setAcadSubj(String acadSubj) {
        this.acadSubj = acadSubj;
    }

    public String getAcadSubj() {
        return this.acadSubj;
    }


    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof QnaPost)) {
            return false;
        }
         
        QnaPost othQnaPost = (QnaPost) o;
         
        return othQnaPost.getId().equals(this.getId());
    }
}