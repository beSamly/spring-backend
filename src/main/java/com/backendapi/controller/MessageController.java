package com.backendapi.controller;

import com.backendapi.constants.EVENT_TYPE;
import com.backendapi.dto.SuccessResponse;
import com.backendapi.dto.message.CreateMessageDTO;
import com.backendapi.dto.message.MessageDTO;
import com.backendapi.entity.maindb.*;
import com.backendapi.event.Event;
import com.backendapi.manager.EventManager;
import com.backendapi.repository.MessageRepository;
import com.backendapi.service.MessageService;
import com.backendapi.socket_message.MessageAlert;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageRepository messageRepository;
    private final EventManager eventManager;
    private final MessageService messageService;

    public MessageController(MessageRepository messageRepository, MessageService messageService, EventManager eventManager) {
        this.messageRepository = messageRepository;
        this.eventManager = eventManager;
        this.messageService = messageService;
    }

    @GetMapping("/{channelId}")
    public SuccessResponse<List<MessageDTO>> getMessageOfChannel(@PathVariable @Valid Long channelId) {
        List<Message> messages = this.messageRepository.findByChannelId(channelId);
        return new SuccessResponse<List<MessageDTO>>(messages.stream().map(Message::toDTO).collect(Collectors.toList()));
    }

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        List<Message> messages = this.messageRepository.findAll();
        Message m1 = messages.get(0);
        Channel channel = m1.getChannel();
        m1.setChannel(channel);
        return messages;
    }

    @PostMapping("/create")
    public SuccessResponse<MessageDTO> sendMessageToChannel(@RequestBody @Valid CreateMessageDTO createMessageDTO, @RequestAttribute("user") User user, @RequestAttribute("channel") Channel channel) {

        Message newMessage = new Message();
        newMessage.setSenderId(user.getId());
        newMessage.setChannel(channel);
        newMessage.setContent(createMessageDTO.getContent());
        this.messageService.saveMessage(newMessage);
//        this.messageRepository.save(newMessage);

        List<Integer> receiverIds = channel.getChannelMemberIds();

        MessageAlert message = new MessageAlert();
        message.setReceiverIds(receiverIds);
        message.setChannelId(channel.getChannelId());
        message.setContent(createMessageDTO.getContent());


        this.eventManager.emit(new Event(EVENT_TYPE.MESSAGE_TO_BROADCAST_SERVER, message.toString()));
        return new SuccessResponse<MessageDTO>(newMessage.toDTO());
    }
}
