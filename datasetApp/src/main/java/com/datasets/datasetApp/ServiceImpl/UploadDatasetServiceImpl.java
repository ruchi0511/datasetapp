package com.datasets.datasetApp.ServiceImpl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;
import com.datasets.datasetApp.Repository.DawjonesIndexRepository;
import com.datasets.datasetApp.Service.UploadDatasetService;
import com.datasets.datasetApp.util.CSVHelper;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class UploadDatasetServiceImpl implements UploadDatasetService {

	@Autowired
	DawjonesIndexRepository repository;
	
	public void uploadDataset(MultipartFile file) throws Exception {
		try {
		      List<DawjonesIndexEntity> dawjonesIndex = CSVHelper.csvTodawjonesIndex(file.getInputStream());
		      repository.saveAll(dawjonesIndex);
		    } catch (Exception e) {
		    	throw new Exception("Error occurred while storing csv data: " + e.getMessage());
		    }
		
	}
	
	

}
