package com.datasets.datasetApp.Repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;


@Repository
public interface DawjonesIndexRepository extends JpaRepository<DawjonesIndexEntity, Long>{

	List<DawjonesIndexEntity> findByStock(String stockTicker);


}
