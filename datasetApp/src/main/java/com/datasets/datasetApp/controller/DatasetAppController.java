package com.datasets.datasetApp.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;
import com.datasets.datasetApp.Service.AddRecordService;
import com.datasets.datasetApp.Service.QueryDataService;
import com.datasets.datasetApp.Service.UploadDatasetService;
import com.datasets.datasetApp.util.CSVHelper;
import com.datasets.datasetApp.util.ResponseMessage;

@RestController
public class DatasetAppController {
	
	Logger logger = LoggerFactory.getLogger(DatasetAppController.class);

	@Autowired
	UploadDatasetService uploadDatasetService;
	
	@Autowired
	QueryDataService queryDataService;
	
	@Autowired
	AddRecordService addRecordService;

	@PostMapping("/datasetapp/uploaddataset")
	public ResponseEntity<ResponseMessage> uploadDataset(@RequestParam("file") MultipartFile file) {
		
		String message = "";
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				uploadDatasetService.uploadDataset(file);
				message = "File successfully uploaded: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}catch (Exception e) {
				logger.error(e.getMessage());
				message = "File could not be uploaded: " + file.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
			}
		}
		message = "Bad file format, upload a csv file";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	 
		
	}

	
	@GetMapping("/datasetapp/getdatabystockticker")
	public ResponseEntity<List<DawjonesIndexEntity>> getData(@RequestParam String stockTicker) {
		try{
			List<DawjonesIndexEntity> dawjonesIndex = queryDataService.getData(stockTicker);
			
			if (dawjonesIndex.isEmpty()) {
		        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		    }
			
			return new ResponseEntity<>(dawjonesIndex, HttpStatus.OK);
		}catch (Exception e) {
			logger.error(e.getMessage());
		    return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	}
	
	@PostMapping("/datasetapp/addrecord")
	public ResponseEntity<ResponseMessage> addRecord(@RequestParam("file") MultipartFile file) {
		String message = "";
		if (CSVHelper.hasCSVFormat(file)) {
			try {
				addRecordService.addRecord(file);
				message = "Successfully added the record";
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			}catch (Exception e) {
				logger.error(e.getMessage());
				message = "Record could not be added";
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ResponseMessage(message));
			}
		}
		message = "Bad file format, upload a csv file";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	 
	}
	
	
}
