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

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<UserSentenceBO> getByUserId(String userId) {
        return userSentenceRepository.findUserSentenceByUserId(userId).stream()
                .map(UserSentenceConverter::toUserSentenceBO).collect(Collectors.toList());
    }

    @Override
    public void publish(String userId, String word) {
        producerTemplate.sendBodyAndHeader("direct:start", word, "userId", userId);
    }

    @Override
    public void persistSentence(String userId, String sentence) {
        userSentenceRepository.save(new UserSentence(userId, LocalDateTime.now(), sentence));
    }
}
