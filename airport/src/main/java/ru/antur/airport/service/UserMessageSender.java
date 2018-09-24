package ru.antur.airport.service;


import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antur.airport.config.RabbitConfig;
import ru.antur.airport.model.User;

@Service
public class UserMessageSender {
    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserMessageSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(User user) {
        this.rabbitTemplate.convertAndSend(RabbitConfig.QUEUE_ORDERS, user);

    }
}
