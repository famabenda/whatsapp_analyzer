package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class WhatsappMessage {
    private MessageSender messageSender;
    private LocalDateTime dateTime;
    private String message;


    @Override
    public String toString() {
        return "Date: " + dateTime.toString() +
                " Sender: " + messageSender.toString() +
                " message: " + message;
    }
}
