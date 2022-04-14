package com.rosmat.warehouse.model.repository;

import com.querydsl.core.types.Predicate;
import com.rosmat.warehouse.model.AggregationParamEnum;
import com.rosmat.warehouse.model.AggregationFunctionEnum;
import com.rosmat.warehouse.model.GroupingParamEnum;
import com.rosmat.warehouse.dto.StatisticReportDto;

import java.util.List;

public interface GroupingByStatisticCustomRepository {

    List<StatisticReportDto> findAllWithGroupBy(Predicate predicate, GroupingParamEnum groupingParamEnum,
                                                AggregationFunctionEnum aggregationFunctionEnum, AggregationParamEnum aggregationParamEnum);
}
