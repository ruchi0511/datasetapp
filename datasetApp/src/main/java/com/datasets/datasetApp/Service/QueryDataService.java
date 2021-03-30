package com.datasets.datasetApp.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;

@Service
public interface QueryDataService {

	List<DawjonesIndexEntity> getData(String stockTicker);
}
