package com.entity;

import lombok.Data;

import java.util.HashMap;

@Data
public class CharCountProportion {

    private HashMap<String, Long> proportions = new HashMap<>();


    public void countChar(WhatsappMessage message) {
        String key = message.getMessageSender().getName();
        proportions.merge(key, calcCharCount(message), Long::sum);
    }

    private Long calcCharCount(WhatsappMessage message) {
        return (long) message.getMessage()
                .trim().replace(" ", "")
                .toCharArray()
                .length;
    }
}
