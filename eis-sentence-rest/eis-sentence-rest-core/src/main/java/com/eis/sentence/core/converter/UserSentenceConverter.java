package com.eis.sentence.core.converter;

import com.eis.sentence.core.bo.UserSentenceBO;
import com.eis.sentence.data.entity.UserSentence;

public final class UserSentenceConverter {

    private UserSentenceConverter () {}

    public static UserSentenceBO toUserSentenceBO(UserSentence userSentence) {
        UserSentenceBO bo = new UserSentenceBO();
        bo.setDate(userSentence.getDate());
        bo.setSentence(userSentence.getSentence());
        bo.setUserId(userSentence.getUserId());
        return bo;
    }
}
