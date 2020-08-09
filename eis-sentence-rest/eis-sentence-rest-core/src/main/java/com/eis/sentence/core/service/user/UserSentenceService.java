package com.eis.sentence.core.service.user;

import com.eis.sentence.core.bo.UserSentenceBO;
import rx.Completable;
import rx.Observable;

/**
 * @author Maksym Khudiakov
 */
public interface UserSentenceService {

    Completable publish(String userId, String word);

    void persistSentence(String userId, String sentence);

    Observable<UserSentenceBO> getByUserId(String userId, int page, int limit);
}
