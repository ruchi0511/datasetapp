package com.datasets.datasetApp.ServiceImpl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;
import com.datasets.datasetApp.Repository.DawjonesIndexRepository;
import com.datasets.datasetApp.Service.AddRecordService;
import com.datasets.datasetApp.util.CSVHelper;

@Service
public class AddRecordServiceImpl implements AddRecordService{

	Logger logger = LoggerFactory.getLogger(AddRecordServiceImpl.class);

	@Autowired
	DawjonesIndexRepository repository;
	
	@Override
	public String addRecord(MultipartFile file) throws Exception {
		try {
		      List<DawjonesIndexEntity> dawjonesIndex = CSVHelper.csvTodawjonesIndex(file.getInputStream());
		      String msg;
		      if(dawjonesIndex.isEmpty()){
		    	  msg = "No record present in csv file";
		    	  return msg;
		      }
		      DawjonesIndexEntity dawjonesIndexEntity = dawjonesIndex.get(0);
		      
		      String stock = dawjonesIndexEntity.getStock();
		      String date = dawjonesIndexEntity.getDate();
		      Optional<DawjonesIndexEntity> optional = repository.findByStockAndDate(stock, date);
		      
		      
		      if(!optional.isPresent()){
		    	  repository.save(dawjonesIndexEntity);
		    	  msg = "Record added successfully";
		    	  logger.info(msg);

		      }else{
		    	  msg = "Record for stock :"+stock+" for the week of :"+date+" already present";
		    	  logger.info(msg);
		      }
		      
		    return msg;  
		    } catch (Exception e) {
		    	logger.error(e.getMessage());
		    	throw new Exception("Error occurred while storing csv data: " + e.getMessage());
		    }
		
	}

}
