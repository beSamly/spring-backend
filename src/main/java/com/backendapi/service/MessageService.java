package com.backendapi.service;

import com.backendapi.entity.maindb.Message;
import com.backendapi.repository.MessageRepository;
import org.springframework.stereotype.Component;

@Component
public class MessageService {
    private final MessageRepository messageRepository;

    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveMessage(Message message) {
        this.messageRepository.save(message);
    }
}
