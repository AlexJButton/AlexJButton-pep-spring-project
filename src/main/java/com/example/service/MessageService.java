package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

@Service
public class MessageService {

    MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }


    /**
     * Find if there is an existing message by the ID.
     * @param The ID to check for.
     * @return True if the message exists, false if not.
     */
    public boolean checkMessage(int message_id){
        return messageRepository.existsById(message_id);
    }


    /**
     * Persist a new message entity.
     * @param message a transient message entity.
     * @return The persisted message entity.
     */
    public Message addMessage(Message message) {
        return messageRepository.save(message);
    }


    /**
     * Get all the messages in the database.
     * @return List of all the messages.
     */
    public List<Message> getMessages() {
        return messageRepository.findAll();
    }


    /**
     * Find a messsage by the ID.
     * @param An int containing the message ID.
     * @return The existing message entity or null if not found.
     */
    public Message getMessageByID(int message_id) {
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            return message;
        }
        return null;
    }


    /**
     * Delete a messsage by the ID.
     * @param An int containing the message ID.
     * @return The persisted message entity.
     */
    public Integer deleteMessageByID(int message_id) {
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(message_id);
            return 1;
        }
        return null;
    }


    /**
     * Update a messsage by the ID.
     * @param An int containing the message ID and a string containing new text.
     * @return The persisted message entity.
     */
    public int updateMessageByID(int message_id, String message_text) {
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setMessage_text(message_text);
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }


    /**
     * Find all messages made by an account
     * @param An int containing the account ID.
     * @return The persisted message entity.
     */
    public List<Message> getMessageByAccount(int account_id) {
        List<Message> allMessages = messageRepository.findAll();
        List<Message> matchingMessages = new ArrayList<Message>();
        for (int i = 0; i < allMessages.size(); i++) {
            if (allMessages.get(i).getPosted_by() == account_id) {
                matchingMessages.add(allMessages.get(i));
            }
        }
        return matchingMessages;
    }


}
