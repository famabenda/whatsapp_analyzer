package com;


import com.entity.WhatsappChat;
import com.exceptions.NotFoundException;
import com.service.ChatReader;
import com.utlis.IOService;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
public class Application {

    private static ChatReader reader = new ChatReader();
    private static IOService ioService = new IOService();


    public static void main(String[] args) throws IOException, NotFoundException {
        JFileChooser chooser = new JFileChooser();
        chooser.showOpenDialog(null);
        File selectedFile = chooser.getSelectedFile();
        List<String> strings = ioService.readWhatsappFile(selectedFile);
        WhatsappChat chat = reader.read(strings);
        System.out.println(chat.toString());
    }
}
