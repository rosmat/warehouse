package com.rosmat.warehouse.model.repository;

import com.rosmat.warehouse.model.QStatistic;
import com.rosmat.warehouse.model.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public interface StatisticRepository extends JpaRepository<Statistic, Long>, QuerydslPredicateExecutor<Statistic>,
        QuerydslBinderCustomizer<QStatistic>, GroupingByStatisticCustomRepository {

    @Override
    default void customize(QuerydslBindings bindings, QStatistic root) {
        bindings.bind(root.eventDate)
                .all((path, value) -> {
                    List<? extends LocalDate> dates = new ArrayList<>(value);
                    if (dates.size() == 1) {
                        return Optional.of(path.eq(dates.get(0)));
                    } else {
                        LocalDate from = dates.get(0);
                        LocalDate to = dates.get(1);
                        return Optional.of(path.between(from, to));
                    }
                });
    }
}
