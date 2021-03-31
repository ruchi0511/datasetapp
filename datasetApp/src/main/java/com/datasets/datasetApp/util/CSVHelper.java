package com.datasets.datasetApp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.datasets.datasetApp.Entity.DawjonesIndexEntity;

public class CSVHelper {
	
	static Logger logger = LoggerFactory.getLogger(CSVHelper.class);

	public static boolean hasCSVFormat(MultipartFile file) {

	    if (!"text/csv".equals(file.getContentType())) {
	      return false;
	    }

	    return true;
	  }
	
	public static List<DawjonesIndexEntity> csvTodawjonesIndex(InputStream is) throws Exception {
		
		try (
				BufferedReader fileReader = new BufferedReader(
				new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
				){
			List<DawjonesIndexEntity> dawjonesIndex = new ArrayList<DawjonesIndexEntity>();

			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				int quarter =0;
				if(csvRecord.get("quarter") != null && !csvRecord.get("quarter").equals("")){
					quarter=Integer.parseInt(csvRecord.get("quarter"));
					
				}
				Long volume = 0L;
				if(csvRecord.get("volume") != null && !csvRecord.get("volume").equals("")){
					volume = Long.parseLong(csvRecord.get("volume"));
				}
				Double changePrice = 0.0;
				if(csvRecord.get("percent_change_price") != null && !csvRecord.get("percent_change_price").equals("")){
					changePrice = Double.parseDouble(csvRecord.get("percent_change_price"));
				}
				Double changeVol = 0.0;
				if(csvRecord.get("percent_change_volume_over_last_wk") != null && !csvRecord.get("percent_change_volume_over_last_wk").equals("")){
					changeVol = Double.parseDouble(csvRecord.get("percent_change_volume_over_last_wk"));
				}
				Long privWk = 0L;
				if(csvRecord.get("previous_weeks_volume") != null && !csvRecord.get("previous_weeks_volume").equals("")){
					privWk = Long.parseLong(csvRecord.get("previous_weeks_volume"));
				}
				Double nextWk = 0.0;
				if(csvRecord.get("percent_change_next_weeks_price") != null && !csvRecord.get("percent_change_next_weeks_price").equals("")){
					nextWk = Double.parseDouble(csvRecord.get("percent_change_next_weeks_price"));
				}
				int div=0;
				if(csvRecord.get("days_to_next_dividend") != null && !csvRecord.get("days_to_next_dividend").equals("")){
					div = Integer.parseInt(csvRecord.get("days_to_next_dividend"));
				}
				Double nextDiv = 0.0;
				if(csvRecord.get("percent_return_next_dividend") != null && !csvRecord.get("percent_return_next_dividend").equals("")){
					nextDiv = Double.parseDouble(csvRecord.get("percent_return_next_dividend"));
				}
				
				DawjonesIndexEntity dawjonesIndexEntity = new DawjonesIndexEntity(
						quarter,
						csvRecord.get("stock"),
						csvRecord.get("date"),
						csvRecord.get("open"),
						csvRecord.get("high"),
						csvRecord.get("low"),
						csvRecord.get("close"),
						volume,
						changePrice,
						changeVol,
						privWk,
						csvRecord.get("next_weeks_open"),
						csvRecord.get("next_weeks_close"),
						nextWk,
						div,
						nextDiv

				);

				dawjonesIndex.add(dawjonesIndexEntity);
			}

			return dawjonesIndex;
		} catch (Exception e) {
			logger.error(e.getMessage());
			throw new Exception("Error occurred while parsing CSV file: "+ e.getMessage());
		}

	  }
}
