package ru.antur.airport.service;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import ru.antur.airport.config.RabbitConfig;
import ru.antur.airport.model.User;

import java.util.List;

@Component
public class UserMessageListener {
    static final Logger logger = LoggerFactory.getLogger(UserMessageListener.class);
    private final List<User> messageList;

    public UserMessageListener(List<User> messageList) {
        this.messageList = messageList;
    }

    @RabbitListener(queues = RabbitConfig.QUEUE_ORDERS)
    public void process(User user) {
        logger.info("Received user {}", user.toString());
        messageList.add(user);
    }
}
