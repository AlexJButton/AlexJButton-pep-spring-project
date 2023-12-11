package com.example.service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.entity.Account;
import com.example.repository.AccountRepository;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }


    /**
     * Find if there is an existing account by the username.
     * @param The username to check for.
     * @return True if the username account exists, false if not.
     */
    public boolean checkAccount(String username){
        return accountRepository.existsByUsername(username);
    }


    /**
     * Persist a new account entity.
     * @param Account a transient account entity.
     * @return The persisted account entity.
     */
    public Account addAccount(Account account){
        return accountRepository.save(account);
    }


    /**
     * Login a user and return the account information if successful.
     * @param The username to check with.
     * @param The password to check with.
     * @return The persisted account entity.
     */
    public Account loginAccount(String username, String password){
        Optional<Account> optionalAccount = accountRepository.findByUsername(username);
        if (optionalAccount.isPresent()) {
            Account existingAccount = optionalAccount.get();
            if (existingAccount.getPassword() == password) {
                return existingAccount;
            }
        }
        return null;
    }


}
