package com.eis.sentence.api.controller;

import com.eis.sentence.core.bo.UserSentenceBO;
import com.eis.sentence.core.service.user.UserSentenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.MediaType.ALL_VALUE;

/**
 * @author Maksym Khudiakov
 */
@RestController
public class UserSentenceController {

    private final UserSentenceService userSentenceService;

    @Autowired
    public UserSentenceController(UserSentenceService userSentenceService) {
        this.userSentenceService = userSentenceService;
    }

    @GetMapping(value = "/users/{user_id}/sentences", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    public List<UserSentenceBO> getUserSentences(@PathVariable("user_id") String userId) {
        return userSentenceService.getByUserId(userId);
    }

    @GetMapping(value = "/users/{user_id}/words/{word}", consumes = ALL_VALUE, produces = APPLICATION_JSON_VALUE)
    public void publish(@PathVariable("user_id") String userId, @PathVariable("word") String word) {
        userSentenceService.publish(userId, word);
    }
}
