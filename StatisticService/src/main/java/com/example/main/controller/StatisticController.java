package com.example.main.controller;

import com.example.main.dto.StatisticDTO;
import com.example.main.service.implementation.StatisticService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/statistics")
public class StatisticController {
    private final StatisticService statisticService;

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
        statisticService.addStatistic(statisticDTO);
        return statisticDTO;
    }

    @GetMapping
    public List<StatisticDTO> findAll() {
        return statisticService.findAll();
    }
}
