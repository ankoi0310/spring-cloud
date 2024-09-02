package it.cwk.accountservice.controller;

import it.cwk.accountservice.client.NotificationService;
import it.cwk.accountservice.client.StatisticService;
import it.cwk.accountservice.model.AccountDTO;
import it.cwk.accountservice.model.MessageDTO;
import it.cwk.accountservice.model.StatisticDTO;
import it.cwk.accountservice.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {
    private final AccountService accountService;
    private final StatisticService statisticService;
    private final NotificationService notificationService;

    @GetMapping
    public List<AccountDTO> getAll() {
        statisticService.add(new StatisticDTO("All accounts are requested", new Date()));
        return accountService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        statisticService.add(new StatisticDTO("Account with id " + id + " is requested", new Date()));
        return Optional.ofNullable(accountService.getById(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public AccountDTO add(@RequestBody AccountDTO accountDTO) {
        accountService.add(accountDTO);

        // Log statistic
        statisticService.add(new StatisticDTO("Account " + accountDTO.getUsername() + " is created", new Date()));

        // Send notification
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setFrom("huynhvahuuan3620@gmail.com");
        messageDTO.setTo(accountDTO.getUsername());
        messageDTO.setToName(accountDTO.getName());
        messageDTO.setSubject("Welcome to our system");
        messageDTO.setContent("Your account is created successfully");
        notificationService.sendNotification(messageDTO);
        return accountDTO;
    }

    @PutMapping
    public void update(@RequestBody AccountDTO accountDTO) {
        accountService.update(accountDTO);
        statisticService.add(new StatisticDTO("Account " + accountDTO.getUsername() + " is updated", new Date()));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        accountService.delete(id);
        statisticService.add(new StatisticDTO("Account with id " + id + " is deleted", new Date()));
    }

    @PutMapping("/change-password")
    public void changePassword(@RequestBody AccountDTO accountDTO) {
        accountService.changePassword(accountDTO);
    }
}
