package com.rosmat.warehouse.controller;

import com.querydsl.core.types.Predicate;
import com.rosmat.warehouse.model.Statistic;
import com.rosmat.warehouse.model.repository.StatisticRepository;
import com.rosmat.warehouse.parser.StatisticRowLoader;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticRowLoader statisticRowLoader;
    private final StatisticRepository statisticRepository;

    public StatisticsController(StatisticRowLoader statisticRowLoader, StatisticRepository statisticRepository) {
        this.statisticRowLoader = statisticRowLoader;
        this.statisticRepository = statisticRepository;
    }

    @GetMapping("import")
    public void importData() throws IOException {
        statisticRowLoader.importDataFromCsvToDatabase();
    }

    @GetMapping
    @ResponseBody
    public Iterable<Statistic> findAllByWebQuerydsl(@QuerydslPredicate(root = Statistic.class) Predicate predicate) {
        return statisticRepository.findAll(predicate);
    }
}
