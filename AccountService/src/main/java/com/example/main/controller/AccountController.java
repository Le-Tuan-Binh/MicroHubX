package com.example.main.controller;

import com.example.main.client.INotificationService;
import com.example.main.client.IStatisticService;
import com.example.main.dto.AccountDTO;
import com.example.main.dto.MessageDTO;
import com.example.main.dto.StatisticDTO;
import com.example.main.service.implementation.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final IStatisticService statisticService;
    private final INotificationService notificationService;
    private final Logger logger = LoggerFactory.getLogger(AccountController.class);

    public AccountController(AccountService accountService, IStatisticService statisticService, INotificationService notificationService) {
        this.accountService = accountService;
        this.statisticService = statisticService;
        this.notificationService = notificationService;
    }

    @PostMapping
    public AccountDTO addAccount(@RequestBody AccountDTO accountDTO) {
        accountService.add(accountDTO);
        statisticService.addStatistic(new StatisticDTO("Account " + accountDTO.getUsername() + " is created", new Date()));
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("TBinTB0003@gmail.com");
        messageDTO.setTo("ltbinh21@clc.fitus.edu.vn");
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("MicroHubX – Your Account is Now Active!");

        String pattern = """
                Dear %s,
                
                Welcome to MicroHubX! 🎉
                
                Your account has been successfully created. You can now log in and start exploring all the features we offer.
                
                Here are your account details:
                - Username: %s
                - Registration Date: %s
                
                If you did not create this account, please contact our support team immediately.
                
                Best regards,
                The MicroHubX Team
                """;

        String content = String.format(pattern, accountDTO.getName(), accountDTO.getUsername(), new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        messageDTO.setContent(content);
        notificationService.sendNotification(messageDTO);
        return accountDTO;
    }

    @GetMapping
    public List<AccountDTO> getAccounts() {
        logger.info("AccountService -> AccountController.getAccounts()");
        statisticService.addStatistic(new StatisticDTO("Get all accounts", new Date()));
        return accountService.findAll();
    }

    @DeleteMapping("/{account_id}")
    public void deleteAccount(@PathVariable(name = "account_id") Long account_id) {
        AccountDTO account = accountService.findById(account_id);
        statisticService.addStatistic(new StatisticDTO("Account " + account.getUsername() + " is deleted", new Date()));
        accountService.delete(account_id);
    }

    @PutMapping
    public void updateAccount(@RequestBody AccountDTO accountDTO) {
        statisticService.addStatistic(new StatisticDTO("Account " + accountDTO.getUsername() + " is updated", new Date()));
        accountService.update(accountDTO);
    }

    @GetMapping("/{account_id}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable(name = "account_id") Long id) {
        return Optional.of(new ResponseEntity<AccountDTO>(accountService.findById(id), HttpStatus.OK))
                .orElse(new ResponseEntity<AccountDTO>(HttpStatus.NOT_FOUND));
    }

}
