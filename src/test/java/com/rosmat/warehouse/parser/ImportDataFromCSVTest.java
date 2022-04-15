package com.rosmat.warehouse.parser;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ImportDataFromCSVTest {

    @Test
    void shouldParseWithoutException() throws IOException {
        List<StatisticRow> statisticRowList = new ImportDataFromCSV().parse("statistics_test.csv");
        assertThat(statisticRowList).isNotNull();
        assertThat(statisticRowList).hasSize(10);

        StatisticRow expectedResult = new StatisticRow("Google Ads", "Adventmarkt Touristik",
                LocalDate.of(2019, 11, 12), 7L, 22425L);
        StatisticRow statisticRow = statisticRowList.get(0);
        assertThat(statisticRow).isEqualTo(expectedResult);
    }
}
