package it.cwk.statisticservice.service;

import it.cwk.statisticservice.entity.Statistic;
import it.cwk.statisticservice.model.StatisticDTO;
import it.cwk.statisticservice.repository.StatisticRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StatisticService {
    void add(StatisticDTO statisticDTO);
    List<StatisticDTO> getAll();
}

@Transactional
@Service
@RequiredArgsConstructor
class StatisticServiceImpl implements StatisticService {
    private final StatisticRepository statisticRepository;
    private final ModelMapper modelMapper;

    @Override
    public void add(StatisticDTO statisticDTO) {
        Statistic statistic = modelMapper.map(statisticDTO, Statistic.class);
        statisticRepository.save(statistic);
    }

    @Override
    public List<StatisticDTO> getAll() {
        List<Statistic> statistics = statisticRepository.findAll();

        return statistics.stream()
                .map(statistic -> modelMapper.map(statistic, StatisticDTO.class))
                .toList();
    }
}
