package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MessageSender {
    private String name;

    public String toString() {
        return name;
    }
}
