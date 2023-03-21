package com.denar.cheeper.datalayer.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "messages")
@Getter
@Setter
@ToString
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String text;
    private String username;
    private String messageDate;

    public Message(String text, String username, String messageDate) {
        this.text = text;
        this.username = username;
        this.messageDate = messageDate;
    }

    public Message() {}
}
