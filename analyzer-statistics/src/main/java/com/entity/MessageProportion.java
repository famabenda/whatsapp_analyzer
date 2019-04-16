package com.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class MessageProportion {

    private HashMap<String, Integer> proportions = new HashMap<>();


    public void countMsg(WhatsappMessage message) {
        String key = message.getMessageSender().getName();
        proportions.merge(key, 1, Integer::sum);
    }
}
