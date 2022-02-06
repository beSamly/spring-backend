package com.backendapi.controller;

import com.backendapi.DeliveryServerClient;
import com.backendapi.dto.ResponseDTO;
import com.backendapi.dto.channel.CreateChannelDTO;
import com.backendapi.dto.group.CreateGroupDTO;
import com.backendapi.dto.message.CreateMessageDTO;
import com.backendapi.dto.message.MessageDTO;
import com.backendapi.entity.maindb.*;
import com.backendapi.repository.MessageRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/message")
public class MessageController {

    private final MessageRepository messageRepository;
    private final DeliveryServerClient deliveryServerClient;

    public MessageController(MessageRepository messageRepository, DeliveryServerClient deliveryServerClient) {
        this.messageRepository = messageRepository;
        this.deliveryServerClient = deliveryServerClient;
    }

    @GetMapping("/{channelId}")
    public ResponseDTO<List<MessageDTO>> getMessageOfChannel(@PathVariable @Valid Long channelId) {
        List<Message> messages = this.messageRepository.findByChannelId(channelId);
        return new ResponseDTO<List<MessageDTO>>(messages.stream().map(Message::toDTO).collect(Collectors.toList()));
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
    public ResponseDTO<MessageDTO> sendMessageToChannel(@RequestBody @Valid CreateMessageDTO createMessageDTO, @RequestAttribute("user") User user, @RequestAttribute("channel") Channel channel) {

        Message newMessage = new Message();
        newMessage.setSenderId(user.getId());
        newMessage.setChannel(channel);
        newMessage.setContent(createMessageDTO.getContent());
        this.messageRepository.save(newMessage);

        deliveryServerClient.sendDataToDeliveryServer(createMessageDTO.getContent());
        return new ResponseDTO<MessageDTO>(newMessage.toDTO());
    }

}
