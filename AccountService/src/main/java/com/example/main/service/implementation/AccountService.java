package com.example.main.service.implementation;

import com.example.main.dto.AccountDTO;
import com.example.main.entity.Account;
import com.example.main.repository.AccountRepository;
import com.example.main.service.contract.IAccountService;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AccountService implements IAccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;
    private final Logger logger = LoggerFactory.getLogger(AccountService.class);

    public AccountService(AccountRepository accountRepository, ModelMapper modelMapper) {
        this.accountRepository = accountRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void add(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);
        accountRepository.save(account);
        accountDTO.setId(account.getId());
    }

    @Override
    public void update(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).get();
        if (account != null) {
            modelMapper.addMappings(new PropertyMap<AccountDTO, Account>() {
                @Override
                protected void configure() {
                    skip(destination.getPassword());
                }
            }).map(accountDTO, account);
            accountRepository.save(account);
        }
    }

    @Override
    public void delete(Long id) {
        Account account = accountRepository.getById(id);
        if (account != null) {
            accountRepository.delete(account);
        }
    }

    @Override
    public void updatePassword(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId()).get();
        if (account != null) {
            accountRepository.delete(account);
        }
    }

    @Override
    public List<AccountDTO> findAll() {
        logger.info("AccountService -> AccountService.findAll()");
        List<AccountDTO> accountDTOs = new ArrayList<>();
        accountRepository.findAll().forEach((account) -> {
            accountDTOs.add(modelMapper.map(account, AccountDTO.class));
        });
        return accountDTOs;
    }

    @Override
    public AccountDTO findById(Long id) {
        Account account = accountRepository.getById(id);
        if (account != null) {
            return modelMapper.map(account, AccountDTO.class);
        }
        return null;
    }
}
