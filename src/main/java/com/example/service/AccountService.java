package com.example.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
     ObjectMapper objectMapper;
   
    public Account saveAccount(Account account){
       
        //  Account accountEntity=objectMapper.convertValue(userDto, Account.class);
        //   System.out.println(accountEntity);
         return accountRepository.save(account);
        

    }
    public boolean findUsername(String username){
        Optional<Account> findByUsername = accountRepository.findByUsername(username);
        if(findByUsername.isPresent()){
            return true;
        }
        return false;
    }

     public Account logIn(Account account){
        return accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
     }
}
