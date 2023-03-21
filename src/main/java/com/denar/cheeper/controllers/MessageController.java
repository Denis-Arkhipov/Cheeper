package com.denar.cheeper.controllers;

import com.denar.cheeper.datalayer.entities.Message;
import com.denar.cheeper.datalayer.repositories.MessageRepository;
import com.denar.cheeper.dto.MessageDto;
import com.denar.cheeper.services.MessageService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/messages")
@Data
public class MessageController {
    private final MessageService messageService;

    @GetMapping
    public ResponseEntity<List<Message>> getMessages(
            @RequestParam(required = false) String messageDate) {
        List<Message> messages;

        if (!Objects.equals(messageDate, "") && messageDate != null) {
            messages = messageService.readAfterDate(messageDate);
        } else {
            messages = messageService.readAll();
        }
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<List<Message>> processMessage(
            @RequestBody MessageDto formData, HttpServletRequest request) {

//        CsrfToken token = (CsrfToken) request.getAttribute("_csrf");
//        log.info("{}={}", token.getHeaderName(), token.getToken());

        Message message = messageService.save(formData);
        return new ResponseEntity<>(List.of(message), HttpStatus.CREATED);
    }
}
