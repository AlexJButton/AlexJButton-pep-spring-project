package com.example.service;

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
     * @return The persisted message entity.
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
    public int deleteMessageByID(int message_id) {
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()) {
            messageRepository.deleteById(message_id);
            return 1;
        }
        return 0;
    }


    /**
     * Update a messsage by the ID.
     * @param An int containing the message ID and a string containing new text.
     * @return The persisted message entity.
     */
    public Message updateMessageByID(int message_id, String message_text) {
        Optional<Message> optionalMessage = messageRepository.findById(message_id);
        if (optionalMessage.isPresent()) {
            Message message = optionalMessage.get();
            message.setMessage_text(message_text);
            return messageRepository.save(message);
        }
        return null;
    }


    /**
     * Find all messages made by an account
     * @param An int containing the account ID.
     * @return The persisted message entity.
     */
    public List<Message> getMessageByAccount(int account_id) {
        return messageRepository.findByPostedBy(account_id);
    }


}
