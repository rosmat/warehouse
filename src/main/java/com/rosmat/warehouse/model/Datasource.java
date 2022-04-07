package com.rosmat.warehouse.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
public class Datasource {
    @Id
    private Long id;

    private String name;
}
