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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
@Getter
@Setter
public class Datasource {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "datasource_sequence"
    )
    @SequenceGenerator(
            name = "datasource_sequence",
            sequenceName = "datasource_sequence",
            allocationSize = 5000
    )
    private Long id;

    private String name;
}
