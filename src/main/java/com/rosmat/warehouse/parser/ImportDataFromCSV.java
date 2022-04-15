package com.rosmat.warehouse.parser;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.HeaderColumnNameMappingStrategy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class ImportDataFromCSV {

    public List<StatisticRow> parse(String pathToFile) throws IOException {
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

    private String getFileContent(String pathToFile) throws IOException {
        InputStream is = getClass().getClassLoader().getResourceAsStream(pathToFile);
        String line = "";
        if (is == null) {
            return line;
        }
        InputStreamReader streamReader = new InputStreamReader(is, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(streamReader);
        line = reader.lines().collect(Collectors.joining("\n"));
        reader.close();
        return line;
    }
}
