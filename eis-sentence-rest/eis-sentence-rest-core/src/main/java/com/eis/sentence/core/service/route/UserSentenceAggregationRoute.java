package com.eis.sentence.core.service.route;

import com.eis.sentence.core.service.user.UserSentenceService;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserSentenceAggregationRoute extends RouteBuilder {

    @Value("${spring.broker.kafka.address}")
    private String kafkaAddress;

    private final UserSentenceService userSentenceService;

    @Autowired
    public UserSentenceAggregationRoute(UserSentenceService userSentenceService) {
        super();
        this.userSentenceService = userSentenceService;
    }

    @Override
    public void configure() {
        final String kafkaBaseUrl = String.format("kafka:%s?brokers=%s", kafkaAddress, kafkaAddress);

        from("direct:start").to(String.format("%s&topic=eis-input", kafkaBaseUrl));
        from(String.format("%s&topic=eis-out", kafkaBaseUrl)).process(outExchange -> {
            final String sentence = outExchange.getIn().getBody(String.class);
            userSentenceService.persistSentence(
                outExchange.getIn().getHeader("userId", String.class), sentence);
        });
    }
}
