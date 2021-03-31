package com.datasets.datasetApp.ServiceImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;
import com.datasets.datasetApp.Repository.DawjonesIndexRepository;
import com.datasets.datasetApp.Service.UploadDatasetService;
import com.datasets.datasetApp.util.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UploadDatasetServiceImpl implements UploadDatasetService {
	
	Logger logger = LoggerFactory.getLogger(UploadDatasetServiceImpl.class);

	@Autowired
	DawjonesIndexRepository repository;
	
	public void uploadDataset(MultipartFile file) throws Exception {
		try {
		      List<DawjonesIndexEntity> dawjonesIndex = CSVHelper.csvTodawjonesIndex(file.getInputStream());
		      repository.saveAll(dawjonesIndex);
		    } catch (Exception e) {
		    	logger.error(e.getMessage());
		    	throw new Exception("Error occurred while storing csv data: " + e.getMessage());
		    }
		
	}
	
	

}
