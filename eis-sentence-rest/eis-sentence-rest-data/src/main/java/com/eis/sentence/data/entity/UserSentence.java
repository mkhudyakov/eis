package com.eis.sentence.data.entity;

import org.springframework.data.cassandra.core.cql.PrimaryKeyType;
import org.springframework.data.cassandra.core.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.core.mapping.Table;
import java.time.LocalDateTime;

@Table(value = "user_sentences")
public class UserSentence {

    @PrimaryKeyColumn(name = "user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private final String userId;

    @PrimaryKeyColumn(ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private final LocalDateTime date;

    private final String sentence;

    public UserSentence(String userId, LocalDateTime date, String sentence) {
        this.userId = userId;
        this.date = date;
        this.sentence = sentence;
    }

    public String getUserId() {
        return userId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public String getSentence() {
        return sentence;
    }
}
