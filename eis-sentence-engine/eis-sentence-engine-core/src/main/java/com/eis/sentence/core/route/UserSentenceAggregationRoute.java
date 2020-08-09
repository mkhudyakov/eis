package com.eis.sentence.core.route;

import com.eis.sentence.core.strategy.UserSentenceAggregationStrategy;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserSentenceAggregationRoute extends RouteBuilder {

    @Value("${spring.broker.kafka.address}")
    private String kafkaAddress;

    @Value("${eis.engine.sentence.aggregate.interval.ms}")
    private Long aggregateInterval;

    @Override
    public void configure() throws Exception {
        final String kafkaBaseUrl = String.format("kafka:%s?brokers=%s", kafkaAddress, kafkaAddress);

        from(String.format("%s&topic=eis-input", kafkaBaseUrl))
                .aggregate(header("userId"), new UserSentenceAggregationStrategy())
                .completionInterval(aggregateInterval).to(String.format("%s&topic=eis-out", kafkaBaseUrl));
    }
}
