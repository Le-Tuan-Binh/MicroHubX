package com.example.main.controller;

import com.example.main.dto.StatisticDTO;
import com.example.main.service.implementation.StatisticService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    private final StatisticService statisticService;
    Logger logger = LoggerFactory.getLogger(StatisticController.class);

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping
    public StatisticDTO addStatistic(@RequestBody StatisticDTO statisticDTO) {
        // Try to Fallback in AccountService
        /* try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        } */
        logger.info("StatisticService -> StatisticController.addStatistic()");
        statisticService.addStatistic(statisticDTO);
        return statisticDTO;
    }

    @GetMapping
    public List<StatisticDTO> findAll() {
        return statisticService.findAll();
    }
}
