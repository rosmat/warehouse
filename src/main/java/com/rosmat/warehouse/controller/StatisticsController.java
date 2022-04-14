package com.rosmat.warehouse.controller;

import com.querydsl.core.types.Predicate;
import com.rosmat.warehouse.model.AggregationParamEnum;
import com.rosmat.warehouse.model.AggregationFunctionEnum;
import com.rosmat.warehouse.model.GroupingParamEnum;
import com.rosmat.warehouse.model.Statistic;
import com.rosmat.warehouse.dto.StatisticReportDto;
import com.rosmat.warehouse.model.repository.StatisticRepository;
import com.rosmat.warehouse.parser.StatisticRowLoader;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;

@Validated
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
    public Iterable<Statistic> findAllByWebQuerydsl(@QuerydslPredicate(root = Statistic.class) Predicate predicate) {
        return statisticRepository.findAll(predicate);
    }

    @GetMapping("report")
    public Iterable<StatisticReportDto> findAllWithGroupBy(@QuerydslPredicate(root = Statistic.class) Predicate predicate,
                                                           @NotNull GroupingParamEnum groupingParamEnum, @NotNull AggregationFunctionEnum aggregationFunctionEnum,
                                                           @NotNull AggregationParamEnum aggregationParamEnum) {
        return statisticRepository.findAllWithGroupBy(predicate, groupingParamEnum, aggregationFunctionEnum, aggregationParamEnum);
    }
}
