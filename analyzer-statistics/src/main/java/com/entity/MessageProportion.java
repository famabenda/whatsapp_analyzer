package com.entity;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

@Data
public class MessageProportion {

    private HashMap<String, Integer> proportions = new HashMap<>();


    public void countMsg(WhatsappMessage message) {
        String key = message.getMessageSender().getName();
        proportions.merge(key, 1, Integer::sum);
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        Set<Map.Entry<String, Integer>> entries = proportions.entrySet();
        for (Map.Entry entry : entries) {
            builder.append(" Sender: " + entry.getKey() + " Count: " + entry.getValue());
        }
        return builder.toString();
    }
}
