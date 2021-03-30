package com.datasets.datasetApp.ServiceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;
import com.datasets.datasetApp.Repository.DawjonesIndexRepository;
import com.datasets.datasetApp.Service.AddRecordService;
import com.datasets.datasetApp.util.CSVHelper;

@Service
public class AddRecordServiceImpl implements AddRecordService{

	@Autowired
	DawjonesIndexRepository repository;
	
	@Override
	public void addRecord(MultipartFile file) throws Exception {
		try {
		      List<DawjonesIndexEntity> dawjonesIndex = CSVHelper.csvTodawjonesIndex(file.getInputStream());
		      repository.save(dawjonesIndex.get(0));
		    } catch (Exception e) {
		    	throw new Exception("Error occurred while storing csv data: " + e.getMessage());
		    }
		
	}

}
