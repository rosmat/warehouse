package com.rosmat.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@Entity
@Table
@Getter
@Setter
public class Statistic {
    @Id
    private Long id;

    @ManyToOne
    private Datasource datasource;

    @ManyToOne
    private Campaign campaign;

    private LocalDate eventDate;

    private Long clicks;

    private Long impressions;
}
