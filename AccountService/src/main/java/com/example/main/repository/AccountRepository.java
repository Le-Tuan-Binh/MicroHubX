package com.example.main.repository;

import com.example.main.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account getById(Long id);
}
