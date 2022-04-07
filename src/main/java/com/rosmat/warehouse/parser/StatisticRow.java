package com.rosmat.warehouse.parser;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvDate;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class StatisticRow {
    @CsvBindByName(column = "Datasource")
    private String datasourceName;

    @CsvBindByName(column = "Campaign")
    private String campaignName;

    @CsvDate(value = "MM/dd/yy")
    @CsvBindByName(column = "Daily")
    private LocalDate eventDate;

    @CsvBindByName(column = "Clicks")
    private Long clicks;

    @CsvBindByName(column = "Impressions")
    private Long impressions;
}
