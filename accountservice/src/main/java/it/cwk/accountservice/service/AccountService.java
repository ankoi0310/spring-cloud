package it.cwk.accountservice.service;

import it.cwk.accountservice.entity.Account;
import it.cwk.accountservice.model.AccountDTO;
import it.cwk.accountservice.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    void add(AccountDTO accountDTO);
    void update(AccountDTO accountDTO);
    void changePassword(AccountDTO accountDTO);
    void delete(Long id);
    List<AccountDTO> getAll();
    AccountDTO getById(Long id);
}

@Transactional
@Service
@RequiredArgsConstructor
class AccountServiceImpl implements AccountService {
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(AccountDTO accountDTO) {
        Account account = modelMapper.map(accountDTO, Account.class);

        // hash password

        accountRepository.save(account);

        accountDTO.setId(account.getId());
    }

    @Override
    public void update(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        modelMapper.map(accountDTO, account);

        // hash password

        accountRepository.save(account);
    }

    @Override
    public void changePassword(AccountDTO accountDTO) {
        Account account = accountRepository.findById(accountDTO.getId())
                .orElseThrow(() -> new RuntimeException("Account not found"));

        // hash password

        accountRepository.save(account);
    }

    @Override
    public void delete(Long id) {
        Optional<Account> account = accountRepository.findById(id);
        account.ifPresent(accountRepository::delete);
    }

    @Override
    public List<AccountDTO> getAll() {
        List<Account> accounts = accountRepository.findAll();

        return accounts.stream()
                .map(account -> modelMapper.map(account, AccountDTO.class))
                .toList();
    }

    @Override
    public AccountDTO getById(Long id) {
        Account account = accountRepository.findById(id)
                .orElse(null);

        return modelMapper.map(account, AccountDTO.class);
    }
}
