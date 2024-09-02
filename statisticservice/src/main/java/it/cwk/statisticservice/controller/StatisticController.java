package it.cwk.statisticservice.controller;

import it.cwk.statisticservice.model.StatisticDTO;
import it.cwk.statisticservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/statistic")
@RequiredArgsConstructor
public class StatisticController {
    Logger logger = LoggerFactory.getLogger(getClass());

    private final StatisticService statisticservice;

    @GetMapping
    public List<StatisticDTO> getAll() {
        logger.info("Getting all statistics");
        return statisticservice.getAll();
    }

    @PostMapping
    public StatisticDTO add(@RequestBody StatisticDTO statisticDTO) {
        logger.info("Adding statistic: {}", statisticDTO);

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        statisticservice.add(statisticDTO);
        return statisticDTO;
    }
}
