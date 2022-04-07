package com.rosmat.warehouse.parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ImportDataFromCSV {

    public static List<StatisticRow> parse(String pathToFile) throws IOException {
        String csv = getFileContent(pathToFile);
        HeaderColumnNameMappingStrategy<StatisticRow> strategy = new HeaderColumnNameMappingStrategy<>();
        strategy.setType(StatisticRow.class);
        Reader reader = new StringReader(csv);
        CsvToBean<StatisticRow> csvToBean = new CsvToBeanBuilder<StatisticRow>(reader)
                .withMappingStrategy(strategy)
                .withSeparator(',')
                .build();
        return csvToBean.parse();
    }

    private static String getFileContent(String pathToFile) throws IOException {
        Path fileName = Path.of(pathToFile);
        return Files.readString(fileName);
    }

    private ImportDataFromCSV() {}
}
