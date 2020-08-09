package com.eis.sentence.data.repository;

import com.eis.sentence.data.entity.UserSentence;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.data.repository.Repository;

import java.util.List;

public interface UserSentenceRepository extends Repository<UserSentence, String> {

    @Query("SELECT * from user_sentences WHERE user_id = ?0")
    List<UserSentence> findUserSentenceByUserId(String userId);

    UserSentence save(UserSentence sentence);
}
