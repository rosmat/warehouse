package com.rosmat.warehouse.model.repository;

import com.querydsl.core.Tuple;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.NumberExpression;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQuery;
import com.rosmat.warehouse.model.AggregationParamEnum;
import com.rosmat.warehouse.model.AggregationFunctionEnum;
import com.rosmat.warehouse.model.GroupingParamEnum;
import com.rosmat.warehouse.dto.StatisticReportDto;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.rosmat.warehouse.model.QStatistic.statistic;

@Repository
public class GroupingByStatisticCustomRepositoryImpl implements GroupingByStatisticCustomRepository {
    @PersistenceContext
    private EntityManager entityManager;

    private static final Map<GroupingParamEnum, Path> groupingParamEnumPathMap = Map.of(
            GroupingParamEnum.DATASOURCE, statistic.datasource.name,
            GroupingParamEnum.CAMPAIGN, statistic.campaign.name,
            GroupingParamEnum.DATE, statistic.eventDate
    );

    private static final Map<AggregationParamEnum, NumberPath<Long>> aggregationParamEnumPathMap = Map.of(
            AggregationParamEnum.CLICKS, statistic.clicks,
            AggregationParamEnum.IMPRESSIONS, statistic.impressions
    );

    @Override
    public List<StatisticReportDto> findAllWithGroupBy(Predicate predicate, GroupingParamEnum groupingParamEnum,
                                                       AggregationFunctionEnum aggregationFunctionEnum, AggregationParamEnum aggregationParamEnum) {
        if (groupingParamEnum == null || aggregationFunctionEnum == null || aggregationParamEnum == null) {
            return Collections.emptyList();
        }

        Path groupByPath = groupingParamEnumPathMap.get(groupingParamEnum);
        NumberPath<Long> aggregateByPath = aggregationParamEnumPathMap.get(aggregationParamEnum);
        NumberExpression aggregationFunction = getAggregationFunction(aggregateByPath, aggregationFunctionEnum);

        List<Tuple> tupleList = new JPAQuery<>(entityManager)
                .select(groupByPath, aggregationFunction.as(StatisticReportDto.getValueFieldName()))
                .from(statistic)
                .where(predicate)
                .groupBy(groupByPath)
                .fetch();

        List<StatisticReportDto> resultList = new ArrayList<>();
        tupleList.forEach(tuple -> resultList.add(new StatisticReportDto(tuple.get(0, Object.class).toString(), tuple.get(1, Number.class))));
        return resultList;
    }

    private NumberExpression getAggregationFunction(NumberPath<Long> numberPath, AggregationFunctionEnum aggregationFunctionEnum) {
        switch (aggregationFunctionEnum) {
            case SUM:
                return numberPath.sum();
            case COUNT:
                return numberPath.count();
            case MAX:
                return numberPath.max();
            case MIN:
                return numberPath.min();
            case AVG:
                return numberPath.avg();
            default:
                throw new UnsupportedOperationException("Aggregation function not implemented.");
        }
    }
}
