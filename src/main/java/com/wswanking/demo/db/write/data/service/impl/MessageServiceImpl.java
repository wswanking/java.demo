package com.wswanking.demo.db.write.data.service.impl;

import com.wswanking.demo.db.write.data.domain.Message;
import com.wswanking.demo.db.write.data.mapper.MessageMapper;
import com.wswanking.demo.db.write.data.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageMapper messageMapper;
    @Override
    public void insertMessage(Message message) {
        messageMapper.insertMessage(message);
    }
}
