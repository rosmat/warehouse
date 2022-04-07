package com.rosmat.warehouse.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDate;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Getter
@Setter
public class Statistic {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "statistic_sequence"
    )
    @SequenceGenerator(
            name = "statistic_sequence",
            sequenceName = "statistic_sequence",
            allocationSize = 5000
    )
    private Long id;

    @ManyToOne
    private Datasource datasource;

    @ManyToOne
    private Campaign campaign;

    private LocalDate eventDate;

    private Long clicks;

    private Long impressions;
}
