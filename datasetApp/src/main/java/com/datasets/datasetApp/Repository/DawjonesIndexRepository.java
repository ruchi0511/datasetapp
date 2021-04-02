package com.datasets.datasetApp.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;


@Repository
public interface DawjonesIndexRepository extends JpaRepository<DawjonesIndexEntity, Long>{

	List<DawjonesIndexEntity> findByStock(String stockTicker);

	Optional<DawjonesIndexEntity> findByStockAndDate(String stock, String date);


}
