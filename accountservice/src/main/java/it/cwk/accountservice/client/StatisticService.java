package it.cwk.accountservice.client;

import it.cwk.accountservice.model.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", path = "/statistic", fallback = StatisticServiceImpl.class)
public interface StatisticService {

    @PostMapping
    void add(@RequestBody StatisticDTO statisticDTO);

}

@Component
class StatisticServiceImpl implements StatisticService {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void add(StatisticDTO statisticDTO) {
        logger.error("Statistic service is not available");
    }

}
