package com.datasets.datasetApp.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface AddRecordService {

	void addRecord(MultipartFile file) throws Exception;
}
