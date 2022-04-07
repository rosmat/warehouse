package com.rosmat.warehouse.controller;

import com.rosmat.warehouse.parser.StatisticRowLoader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    private final StatisticRowLoader statisticRowLoader;

    public StatisticsController(StatisticRowLoader statisticRowLoader) {
        this.statisticRowLoader = statisticRowLoader;
    }

    @GetMapping("import")
    public void importData() throws IOException {
        statisticRowLoader.importDataFromCsvToDatabase();
    }
}
