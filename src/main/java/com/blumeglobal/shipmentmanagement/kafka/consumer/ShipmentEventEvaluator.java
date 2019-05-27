package com.blumeglobal.shipmentmanagement.kafka.consumer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Controller;

import com.blumeglobal.shipmentmanagement.enums.Constants;
import com.blumeglobal.shipmentmanagement.model.ShipmentException;
import com.blumeglobal.shipmentmanagement.service.IKafkaShipmentExceptionService;
import com.blumeglobal.shipmentmanagement.utils.DateUtils;


/**
 * 
 *
 */
@Controller
public class ShipmentEventEvaluator {

	@Autowired
	private IKafkaShipmentExceptionService shipmentService;
	
	Constants exceptionType = Constants.valueOf("EXCEPTIONTYPE");
	Constants exceptionNotes = Constants.valueOf("EXCEPTIONNOTES");
	
	private static final Logger logger = LoggerFactory.getLogger(ShipmentEventEvaluator.class);
	List<String> inputEvents = new ArrayList<String>();   

	//@KafkaListener(topics = "${kafka.topic.shipmentException}")
	public void receive(String payload) throws ParseException {

		logger.info("Entering into ShipmentEventEvaluator consumer Arrived at: "+new Date()+ "Message: "+payload);
		// parsing json file 
		Object obj = new JSONParser().parse(payload); 

		// type casting object to JSONObject 
		JSONObject jo = (JSONObject) obj; 

		// getting required values from json
		String eventTime = (String) jo.get("eventTime"); 
		String createDate = (String)  jo.get("createDate");
		String reportSource = (String) jo.get("reportSource");
		String location = (String) jo.get("location");
		
		//string to date conversion using DateUtil
		Date eventDate = DateUtils.convertStringToDate(eventTime, "MMM dd, yyyy HH:mm:ss a");
		Date createdDate = DateUtils.convertStringToDate(createDate, "MMM dd, yyyy HH:mm:ss a");
		//fetching mode of transport value from reportSource
		String modeOfTransport = removeWord(reportSource,"Event");
		Random rand = new Random();

		//Calculating Date difference using dateDifference method
		Long noOfDays = dateDifference(eventDate,createdDate);

		String dateException = noOfDays+" Day";

		//date pickup exception
		if (noOfDays >0) {
			logger.debug("PickUp Delay Exception Generated...");
			
			ShipmentException model = new ShipmentException();
			model.setShipmentId((long) rand.nextInt(1000));
			model.setE2eShipmentId((long) rand.nextInt(1000));
			model.setModeOfTransport(modeOfTransport);
			model.setExceptionType(exceptionType.getConstants().toString());
			model.setExceptionNotes(dateException+" "+exceptionNotes.getConstants().toString());
			model.setOccuranceDate(eventDate);
			model.setExceptionLocation(location);
			model.setShipmentMilestoneId((long) rand.nextInt(1000));
			
			shipmentService.createShipmentException(model);		
		}
		else {
			logger.info("No Exception Generated..");
		}
	 
	}

	public long dateDifference(Date eventDate, Date scheduledDate) {
		long duration  = scheduledDate.getTime() - eventDate.getTime();
		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);
		logger.info("PickUp Delayed in days ..."+diffInDays);
		return diffInDays;
	}
	
	 public static String removeWord(String reportSource, String word) 
	    { 
	        // Check if the word is present in string 
	        // If found, remove it using removeAll() 
	        if (reportSource.contains(word)) { 
	           String tempWord = word; 
	           reportSource = reportSource.replaceAll(tempWord, ""); 
	        } 
	        
	        return reportSource; 
	    } 
}
