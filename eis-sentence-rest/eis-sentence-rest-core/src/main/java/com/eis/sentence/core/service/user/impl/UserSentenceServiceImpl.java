package com.eis.sentence.core.service.user.impl;

import com.eis.sentence.core.bo.UserSentenceBO;
import com.eis.sentence.core.converter.UserSentenceConverter;
import com.eis.sentence.core.service.user.UserSentenceService;
import com.eis.sentence.data.entity.UserSentence;
import com.eis.sentence.data.repository.UserSentenceRepository;
import org.apache.camel.ProducerTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Completable;
import rx.Observable;

import java.time.LocalDateTime;
import java.util.List;

/**
 * {@link UserSentenceService} implementation
 * @author Maksym Khudiakov
 */
@Service
public class UserSentenceServiceImpl implements UserSentenceService {

    private static final Logger LOG = LoggerFactory.getLogger(UserSentenceServiceImpl.class);

    private final UserSentenceRepository userSentenceRepository;
    private final ProducerTemplate producerTemplate;

    @Autowired
    public UserSentenceServiceImpl(UserSentenceRepository userSentenceRepository, ProducerTemplate producerTemplate) {
        this.userSentenceRepository = userSentenceRepository;
        this.producerTemplate = producerTemplate;
    }

    @Override
    public Observable<UserSentenceBO> getByUserId(String userId, int page, int limit) {
        List<UserSentence> userSentences = userSentenceRepository.findUserSentenceByUserId(userId);
        return Observable.from(userSentences)
            .skip(page * limit).limit(limit)
            .map(UserSentenceConverter::toUserSentenceBO);
    }

    @Override
    public Completable publish(String userId, String word) {
        producerTemplate.sendBodyAndHeader("direct:start", word, "userId", userId);
        return Completable.complete();
    }

    @Override
    public void persistSentence(String userId, String sentence) {
        userSentenceRepository.save(new UserSentence(userId, LocalDateTime.now(), sentence));
    }
}
