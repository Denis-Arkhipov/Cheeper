package com.denar.cheeper.services;

import com.denar.cheeper.datalayer.entities.Message;
import com.denar.cheeper.datalayer.repositories.MessageRepository;
import com.denar.cheeper.dto.MessageDto;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Data
@RequiredArgsConstructor
public class MessageService {
    @Autowired
    private final MessageRepository messageRepository;

    public List<Message> readAfterDate(String messageDate) {
        return messageRepository.findAfterDate(messageDate);
    }

    public List<Message> readAll() {
        return messageRepository.findAll();
    }

    public Message save(MessageDto formData) {
        Message message = createMessage(formData);
        messageRepository.save(message);
        return message;
    }

    private Message createMessage(MessageDto messageDto) {
        return new Message(messageDto.getTextMessage(),
                messageDto.getUsername(),
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")));
    }
}
