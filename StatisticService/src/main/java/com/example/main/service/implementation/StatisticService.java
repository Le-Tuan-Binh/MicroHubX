package com.example.main.service.implementation;

import com.example.main.dto.StatisticDTO;
import com.example.main.entity.Statistic;
import com.example.main.repository.StatisticRepository;
import com.example.main.service.contract.IStatisticService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class StatisticService implements IStatisticService {
    private final StatisticRepository statisticRepository;
    private final ModelMapper modelMapper;

    public StatisticService(StatisticRepository statisticRepository, ModelMapper modelMapper) {
        this.statisticRepository = statisticRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void addStatistic(StatisticDTO statisticDTO) {
        Statistic statistic = modelMapper.map(statisticDTO, Statistic.class);
        statisticRepository.save(statistic);
    }

    @Override
    public List<StatisticDTO> findAll() {
        List<StatisticDTO> statisticDTOs = new ArrayList<>();
        statisticRepository.findAll().forEach(statistic -> {
            statisticDTOs.add(modelMapper.map(statistic, StatisticDTO.class));
        });
        return statisticDTOs;
    }


}
