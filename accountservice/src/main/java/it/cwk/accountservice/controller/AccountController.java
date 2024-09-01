package it.cwk.accountservice.controller;

import it.cwk.accountservice.model.AccountDTO;
import it.cwk.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    public List<AccountDTO> getAll() {
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        return Optional.ofNullable(accountService.getById(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AccountDTO add(@RequestBody AccountDTO accountDTO) {
        accountService.add(accountDTO);
        return accountDTO;
    }

    @PutMapping
    public void update(@RequestBody AccountDTO accountDTO) {
        accountService.update(accountDTO);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody AccountDTO accountDTO) {
        accountService.changePassword(accountDTO);
    }
}
