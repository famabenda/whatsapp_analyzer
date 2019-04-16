package com.service;

import com.entity.MessageSender;
import com.entity.WhatsappChat;
import com.entity.WhatsappMessage;
import com.exceptions.NotFoundException;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
@Service
public class ChatReader {

    private static final int TIME_PREFIX_LENGTH = 15;
    private static final String DATE_FORMAT = "dd-MM-yy HH:mm";
    private static final int START_INDEX_NAME = 17;

    public WhatsappChat read(List<String> file) throws NotFoundException {
        WhatsappChat chat = new WhatsappChat();
        for (String line : file) {
            if (isValidMessage(line)) {
                MessageSender messageSender = parseMessageSender(line);
                LocalDateTime time = parseMessageTime(line);
                String message = parseMessage(line);
                chat.addMessage(new WhatsappMessage(messageSender, time, message));
            }
        }
        return chat;
    }

    private LocalDateTime parseMessageTime(String line) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT);
        String time = line.substring(0, TIME_PREFIX_LENGTH)
                .replace(".", "-")
                .replace(",", "");
        return LocalDateTime.parse(time, formatter);
    }

    private MessageSender parseMessageSender(String line) throws NotFoundException {
        String name = line.substring(START_INDEX_NAME, parseMessageBegin(line));
        return new MessageSender(name);
    }

    private int parseMessageBegin(String line) throws NotFoundException {
        char[] lineAsChars = line.toCharArray();
        for (int i = TIME_PREFIX_LENGTH; i < lineAsChars.length; i++) {
            if ((int) lineAsChars[i] == (int) ':') return i;
        }
        throw new NotFoundException("Message beginn konnte nicht evaluiert werden, da kein ':' gefunden werden konnte");
    }

    private String parseMessage(String line) throws NotFoundException {
        return line.substring(parseMessageBegin(line) + 2);
    }

    private boolean isValidMessage(String line) {
        try {
            parseMessageBegin(line);
        } catch (NotFoundException nfe) {
            if (line.contains("Ende-zu-Ende-Verschlüsselung")) return false;
        }
        return true;
    }

}
