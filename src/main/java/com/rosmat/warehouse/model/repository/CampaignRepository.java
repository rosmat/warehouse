package com.rosmat.warehouse.model.repository;

import com.rosmat.warehouse.model.Campaign;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CampaignRepository extends JpaRepository<Campaign, Long> {
}
