package com.example.main.service.contract;

import com.example.main.dto.StatisticDTO;
import java.util.List;

public interface IStatisticService {
    void addStatistic(StatisticDTO statisticDTO);

    List<StatisticDTO> findAll();
}
