package com.eis.sentence.core.bo;

import java.time.LocalDateTime;

public class UserSentenceBO {

    private String userId;
    private LocalDateTime date;
    private String sentence;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }
}
