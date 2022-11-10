package com.learning.kafkaintegration.conf;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class topicCreation {



    @Bean
    public NewTopic wikichangesresponses_og() {

        return TopicBuilder.name("wikichangesresponses")
                .config("max.message.bytes","10485880")
                .partitions(3).build();
    }

    @Bean
    public NewTopic wikichangesresponses_edit() {
        return TopicBuilder.name("wikichangesrequests").partitions(3).build();
    }


}

