package com.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class WhatsappChat {
    private ArrayList<WhatsappMessage> messages;

    public WhatsappChat() {
        messages = new ArrayList();
    }


    public void addMessage(WhatsappMessage message) {
        messages.add(message);

    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (WhatsappMessage message : messages) {
            builder.append(message.toString() + "\n");
        }
        return builder.toString();
    }
}
