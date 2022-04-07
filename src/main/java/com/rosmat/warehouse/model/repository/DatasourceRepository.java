package com.rosmat.warehouse.model.repository;

import com.rosmat.warehouse.model.Datasource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DatasourceRepository extends JpaRepository<Datasource, Long> {
}
