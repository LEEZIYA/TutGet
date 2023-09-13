package nucleus.tutget.qna.domain;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

public class QnaPost {
    private final String id;
    private String qnaString;
    private int upvotes;
    private int downvotes;
    private LocalDate postDate;
    private User poster;

    private static final AtomicInteger idSeq = new AtomicInteger();

    public QnaPost(String qnaString, User poster) {
        this.qnaString = qnaString;
        this.upvotes = 0;
        this.downvotes = 0;
        this.postDate = LocalDate.now();
        this.poster = poster;
        this.id = String.valueOf(idSeq.incrementAndGet());
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

    public User getPoster() {
        return poster;
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
