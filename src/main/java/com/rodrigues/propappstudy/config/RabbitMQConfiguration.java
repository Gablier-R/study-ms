package com.rodrigues.propappstudy.config;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfiguration {

    @Bean
    Queue createQueuePendingMsAnalyzeCredit(){
        return QueueBuilder.durable("proposta-pendente.ms-analise-credito").build();
    }

    @Bean
    Queue createQueuePendingMsNotification(){
        return QueueBuilder.durable("proposta-pendente.ms-notificacao").build();
    }

    @Bean
    Queue createQueueMsDoneMsProposal(){
        return QueueBuilder.durable("proposta-concluida.ms-proposta").build();
    }

    @Bean
    Queue createQueueMsDoneMsNotification(){
        return QueueBuilder.durable("proposta-concluida.ms-notificacao").build();
    }

    @Bean
    RabbitAdmin createRabbitAdmin(ConnectionFactory connectionFactory){
        return new RabbitAdmin(connectionFactory);
    }

    ApplicationListener<ApplicationReadyEvent> initAdmin (RabbitAdmin rabbitAdmin){
        return event -> rabbitAdmin.initialize();
    }
}
