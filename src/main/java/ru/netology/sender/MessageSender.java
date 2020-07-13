package ru.netology.sender;

import java.util.Map;

public interface MessageSender {

    void send(Map<String, String> headers);
}
