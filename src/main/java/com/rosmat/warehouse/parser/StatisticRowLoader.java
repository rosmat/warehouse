package com.rosmat.warehouse.parser;

import com.rosmat.warehouse.model.Campaign;
import com.rosmat.warehouse.model.Datasource;
import com.rosmat.warehouse.model.Statistic;
import com.rosmat.warehouse.model.repository.CampaignRepository;
import com.rosmat.warehouse.model.repository.DatasourceRepository;
import com.rosmat.warehouse.model.repository.StatisticRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StatisticRowLoader {
    private final DatasourceRepository datasourceRepository;
    private final CampaignRepository campaignRepository;
    private final StatisticRepository statisticRepository;

    public StatisticRowLoader(DatasourceRepository datasourceRepository, CampaignRepository campaignRepository, StatisticRepository statisticRepository) {
        this.datasourceRepository = datasourceRepository;
        this.campaignRepository = campaignRepository;
        this.statisticRepository = statisticRepository;
    }

    public void importDataFromCsvToDatabase() throws IOException {
        List<StatisticRow> statisticRowList = ImportDataFromCSV.parse("src/main/resources/statistics.csv"); //TODO: move to properties
        Map<String, Datasource> datasourceNameToIdMap = getDatasourceNameToEntityMap(statisticRowList);
        Map<String, Campaign> campaignNameToIdMap = getCampaignNameToEntityMap(statisticRowList);

        List<Statistic> statisticList = statisticRowList
                .stream().map(row -> Statistic.builder().datasource(datasourceNameToIdMap.get(row.getDatasourceName()))
                        .campaign(campaignNameToIdMap.get(row.getCampaignName()))
                        .eventDate(row.getEventDate())
                        .clicks(row.getClicks()).impressions(row.getImpressions()).build()).toList();

        statisticRepository.saveAllAndFlush(statisticList);
    }

    private Map<String, Datasource> getDatasourceNameToEntityMap(List<StatisticRow> statisticRowList) {
        Set<String> datasourceNameSet = statisticRowList
                .stream()
                .map(StatisticRow::getDatasourceName)
                .collect(Collectors.toSet());

        List<Datasource> dataSourceList = datasourceNameSet
                .stream()
                .map(name -> Datasource.builder().name(name).build())
                .toList();

        return datasourceRepository.saveAllAndFlush(dataSourceList)
                .stream()
                .collect(Collectors.toMap(Datasource::getName, entity -> entity));
    }

    private Map<String, Campaign> getCampaignNameToEntityMap(List<StatisticRow> statisticRowList) {
        Set<String> campaignNameSet = statisticRowList
                .stream()
                .map(StatisticRow::getCampaignName)
                .collect(Collectors.toSet());

        List<Campaign> campaignList = campaignNameSet
                .stream()
                .map(name -> Campaign.builder().name(name).build())
                .toList();

        return campaignRepository.saveAllAndFlush(campaignList)
                .stream()
                .collect(Collectors.toMap(Campaign::getName, entity -> entity));
    }
}
