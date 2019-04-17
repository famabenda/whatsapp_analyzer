package com.service;

import com.entity.CharCountProportion;
import com.entity.MessageProportion;
import com.entity.WhatsappChat;
import com.entity.WhatsappMessage;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class StatisticCalculator {
    private WhatsappChat chat;


    public long calcMessageCount() {
        return chat.getMessages().parallelStream().count();
    }

    public MessageProportion calcMessageProportion() {
        MessageProportion proportion = new MessageProportion();
        List<WhatsappMessage> whatsappMessages = chat.getMessages();
        for (WhatsappMessage message : whatsappMessages) {
            proportion.countMsg(message);
        }
        return proportion;
    }

    public CharCountProportion calcCharProportion() {
        CharCountProportion proportion = new CharCountProportion();
        List<WhatsappMessage> whatsappMessages = chat.getMessages();
        for (WhatsappMessage message : whatsappMessages) {
            proportion.countChar(message);
        }
        return proportion;
    }

}
