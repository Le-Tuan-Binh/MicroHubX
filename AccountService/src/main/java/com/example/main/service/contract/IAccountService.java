package com.example.main.service.contract;

import com.example.main.dto.AccountDTO;
import java.util.List;

public interface IAccountService {
    void add(AccountDTO accountDTO);

    void update(AccountDTO accountDTO);

    void updatePassword(AccountDTO accountDTO);

    void delete(Long id);

    List<AccountDTO> findAll();

    AccountDTO findById(Long id);
}
