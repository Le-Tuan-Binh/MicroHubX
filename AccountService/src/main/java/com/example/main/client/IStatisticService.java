package com.example.main.client;

import com.example.main.dto.StatisticDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", url = "http://localhost:9082", path = "/statistics", fallback = StatisticService.class)
public interface IStatisticService {
    @PostMapping
    StatisticDTO addStatistic(@RequestBody StatisticDTO statisticDTO);
}

@Component
class StatisticService implements IStatisticService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public StatisticDTO addStatistic(StatisticDTO statisticDTO) {
        // Fallback
        logger.error("Statistic service is slow");
        return null;
    }
}

