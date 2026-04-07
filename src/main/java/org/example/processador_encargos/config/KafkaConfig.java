package org.example.processador_encargos.config;


import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic requestStatusTopic() {
        return TopicBuilder.name("request-status-conta")
                .partitions(32)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic responseStatusTopic() {
        return TopicBuilder.name("response-status-conta")
                .partitions(32)
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic lancamentoContabilTopic() {
        return TopicBuilder.name("lancamento-contabil")
                .partitions(32)
                .replicas(1)
                .build();
    }
}