package it.cwk.accountservice.client;

import it.cwk.accountservice.model.StatisticDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", url = "http://localhost:8082/statistic")
public interface StatisticService {

    @PostMapping
    void add(@RequestBody StatisticDTO statisticDTO);

}
