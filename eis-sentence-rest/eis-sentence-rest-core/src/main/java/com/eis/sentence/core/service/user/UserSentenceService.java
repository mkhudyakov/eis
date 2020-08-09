package com.eis.sentence.core.service.user;

import com.eis.sentence.core.bo.UserSentenceBO;

import java.util.List;

/**
 * @author Maksym Khudiakov
 */
public interface UserSentenceService {

    void publish(String userId, String word);

    void persistSentence(String userId, String sentence);

    List<UserSentenceBO> getByUserId(String userId);
}
