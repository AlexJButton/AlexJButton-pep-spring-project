package com.example.controller;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.*;
import com.example.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@Controller
public class SocialMediaController {

    AccountService accountService;
    MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }


    // This is the endpoint to register a new account
    @PostMapping("register")
    public Account registerAccount(@RequestBody Account account) {
        boolean usernameCheck = account.getUsername() != null && !account.getUsername().isEmpty();
        boolean passwordCheck = account.getPassword() != null && account.getPassword().length() >= 4;

        if (!usernameCheck || !passwordCheck) {
            throw new ClientErrorException("");
        }

        if (accountService.checkAccount(account.getUsername())) {
            throw new ConflictingAdditionException("");
        }

        return accountService.addAccount(account);
    }


    // This is the endpoint to login with an account
    @PostMapping("login")
    public Account loginAccount(@RequestBody Account account) {
        Account possibleAccount = accountService.loginAccount(account.getUsername(), account.getPassword());

        if (possibleAccount != null) {
            throw new UnauthorizedException("");
        }

        return possibleAccount;
    }


    // This is the endpoint to create a new message
    @PostMapping("messages")
    public Message createMessage(@RequestBody Message message) {
        boolean message_textCheck = !message.getMessage_text().isEmpty() && !(message.getMessage_text().length() > 255);
        boolean posted_byCheck = accountService.checkAccount(message.getPosted_by());

        if (!message_textCheck || !posted_byCheck) {
            throw new ClientErrorException("");
        }
        
        return messageService.addMessage(message);
    }


    // This is the endpoint to list all of the messages
    @GetMapping("messages")
    public List<Message> listMessages() {
        return messageService.getMessages();
    }




    // An exception handler for when there is a conflicting addition to a table
    @ExceptionHandler(ConflictingAdditionException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public @ResponseBody String handleConflictingAddition(ConflictingAdditionException ex) {
        return "";
    }


    // An exception handler for when there is a general client side error
    @ExceptionHandler(ClientErrorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody String handleClientError(ClientErrorException ex) {
        return "";
    }


    // This is the exception handler for the unauthorized account error
    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public @ResponseBody String handleUnauthorized(UnauthorizedException ex) {
        return "";
    }


}
