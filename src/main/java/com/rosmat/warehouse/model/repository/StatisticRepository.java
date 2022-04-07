package com.rosmat.warehouse.model.repository;

import com.rosmat.warehouse.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatisticRepository extends JpaRepository<Statistic, Long> {
}
