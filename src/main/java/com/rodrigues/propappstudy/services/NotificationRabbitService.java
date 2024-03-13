package com.rodrigues.propappstudy.services;

import com.rodrigues.propappstudy.entity.Proposal;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationRabbitService {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void notify (Proposal proposalResponseDto, String exchange){
        rabbitTemplate.convertAndSend(exchange,"", proposalResponseDto);
    }
}
