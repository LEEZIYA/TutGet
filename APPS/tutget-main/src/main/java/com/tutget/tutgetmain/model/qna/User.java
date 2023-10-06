package com.tutget.tutgetmain.model.qna;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private final String id;
    private String name;
    private UserType userType;
    private static final AtomicInteger idSeq = new AtomicInteger();

    private Set<Question> questionsSet;
    private Set<Answer> answersSet;

    public User(String name, UserType userType) {
        this.name = name;
        this.userType = userType;
        this.id = String.valueOf(idSeq.incrementAndGet());

        questionsSet = new HashSet<>();
        answersSet = new HashSet<>();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserType getUserType() {
        return userType;
    }

    public void addQuestion (Question question) {
        questionsSet.add(question);
    }

    public Set<Question> getquestionsSet() {
        return questionsSet;
    }

    public void addAnswer(Answer answer) {
        answersSet.add(answer);
    }

    public Set<Answer> getanswersSet() {
        return answersSet;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
 
        if (!(o instanceof User)) {
            return false;
        }
         
        User othUser = (User) o;
         
        return othUser.getId().equals(this.getId());
    }
}
