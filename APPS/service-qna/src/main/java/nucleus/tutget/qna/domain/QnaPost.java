package nucleus.tutget.qna.domain;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class QnaPost {
    @Id
    private final String id;
    private String qnaString;
    private int upvotes;
    private int downvotes;
    private LocalDate postDate;
    private String posterId;
    private String posterName;
    private Subject subject;

    private static final AtomicInteger idSeq = new AtomicInteger();

    public QnaPost() {
        this.id = String.valueOf(idSeq.incrementAndGet());
    }

    public QnaPost(String qnaString, String posterId, String posterName, Subject subject) {
        this.id = String.valueOf(idSeq.incrementAndGet());
        this.qnaString = qnaString;
        this.upvotes = 0;
        this.downvotes = 0;
        this.postDate = LocalDate.now();
        this.posterId = posterId;
        this.posterName = posterName;
        this.subject = subject;
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

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public Subject getSubject() {
        return subject;
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
