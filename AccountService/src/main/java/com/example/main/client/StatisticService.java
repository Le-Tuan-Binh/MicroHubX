package com.example.main.client;

import com.example.main.dto.StatisticDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "statistic-service", url = "http://localhost:9082", path = "/statistics")
public interface StatisticService {
    @PostMapping
    StatisticDTO addStatistic(@RequestBody StatisticDTO statisticDTO);
}
