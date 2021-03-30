package com.datasets.datasetApp.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;
import com.datasets.datasetApp.Repository.DawjonesIndexRepository;
import com.datasets.datasetApp.Service.QueryDataService;

@Service
public class QueryDataServiceImpl implements QueryDataService{

	@Autowired
	DawjonesIndexRepository repository;
	
	
	public List<DawjonesIndexEntity> getData(String stockTicker) {
		return repository.findByStock(stockTicker);
		
		
	}

}
