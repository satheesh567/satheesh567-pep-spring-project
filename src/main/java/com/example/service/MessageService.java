package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ObjectMapper objectMapper;

    public Message createMessage(Message message){
        if(isValidMessage(message)){
    
        return messageRepository.save(message);
        }
        else {
            throw new IllegalArgumentException("Invalid message parameters");
        }
        
    }

    private boolean isValidMessage(Message message) {
        return message.getMessage_text() != null &&
               !message.getMessage_text().isBlank() &&
               message.getMessage_text().length() <= 255 &&
               message.getPosted_by() != null && accountRepository.findAccountByAccountId(message.getPosted_by())!=null; // You might want to perform additional validation for posted_by
               /* Add more validation as needed */
    }

    public List<Message> getAllMessages(){
        return  messageRepository.findAll();
    }

    public Message getMessageById(Integer id){
        return messageRepository.findMessageMessageId(id);
    }

    public Integer deleteByMessageId(Integer messageId){
        return messageRepository.deleteByMessageIdAndReturnCount(messageId);
    }

    public Integer updateMessage(Integer messageId,String messaText){
        return messageRepository.updateMessageTextByMessageId(messageId, messaText);
    }
    public List<Message> findMessageByUser(Integer id){
       return  messageRepository.findByUser(id);
    }


}

