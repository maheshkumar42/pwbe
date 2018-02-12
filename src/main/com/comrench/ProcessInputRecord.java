package main.com.comrench;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import main.com.comrench.record.City;
import main.com.comrench.record.Place;
import main.com.comrench.record.Province;
import main.com.comrench.record.School;

/**
 * Class: ProcessInputRecord
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to receive the record from the ReadInputFile and then validate/process the record. 
 * First it validates if the record is valid. If record is invalid then it logs the message and returns back to 
 * ReadInputFile so that next record can be sent. If record is valid then it processes that record as follows:
 * if input record is good:
 *		- Check if Province already exists 
 *			a). if yes add the population to existing province
 *			b). if no create the new province and add the population to the new province.
 *		- Check if City already exists in the province 
 *			a).	if yes add the population to existing city of that province.
 *			b).	if no create the new city in the province and add the population to the new city of that province.
 *		- Check if School already exists in the City of the province 
 * 			a).	if yes add the population to existing School of that city for that province.
 *			b).	if no create the new School in the city of that province and add the population to the new School of 
 *			that city for that province.
 * 
 * Date created: 20180211
 * 
 */


public class ProcessInputRecord {

	private Place place;
	private Map<String,Place> listOfPlaces;
	private final static Logger logger = LogManager.getLogger(ProcessInputRecord.class);
	private final int columnCount = 4;
	
	/**
	 * This is the constructor of the ProcessInputRecord class which initializes the HashMap for listOfPlaces.
	 * 
	 */	
	
	public ProcessInputRecord() {
		listOfPlaces = new HashMap<>();
	}

	/**
	 * This method is to process the record received from the input file.
	 * @param String Line of the input file as String
	 * @return boolean true if executed successfully else returns false
	 */
	
	public boolean processRecord(String record){
		if(!validateRecord(record)){
			logger.info("Skipping invalid record: "+record);
			return false;
		}
		logger.info("Processing record: "+record);
		String[] columns = record.split("\t");
		//Trimming of the columns in the string array without creating the new array
		Arrays.stream(columns).map(String::trim).toArray(unused -> columns);
		String province = columns[0], city = columns[1], school = columns[2], population = columns[3].substring(1);
		
		int numOfPeople = Integer.parseInt(population);
		if(listOfPlaces.containsKey(province)){
			place = (listOfPlaces.get(province));
			Map<String,Place> listOfCity = place.getListOfPlaces(); 
			place.addPeople(numOfPeople);			
			if(listOfCity.containsKey(city)){
				place = (listOfCity.get(city));
				Map<String,Place> listOfSchool = place.getListOfPlaces();
				place.addPeople(numOfPeople);				
				if(listOfSchool.containsKey(school)){
					place = (listOfSchool.get(school));
					place.addPeople(numOfPeople);
				}else{
					place = new School(numOfPeople);
					listOfSchool.put(school,place);
				}
			}else{
				place = new City(school,numOfPeople);
				listOfCity.put(city,place);
			}
		}else{
			place = new Province(province,city,school,numOfPeople);
			listOfPlaces.put(province,place);
		}		
		return true;
	}

	/**
	 * This method is the getter for the list of places.
	 * @param none
	 * @return Map<String,Place> List of places in the Map
	 */
	
	public Map<String, Place> getListOfPlaces() {
		return listOfPlaces;
	}

	/**
	 * This method is to validate the record from the input file.
	 * @param String Line from input file to be processed
	 * @return boolean true if executed successfully else returns false
	 */
	
	private boolean validateRecord(String record) {
		String[] colArr = record.split("\t");
		if(colArr.length != columnCount){
			logger.error("Column count doesn't match the expected. Expected: " + columnCount + " Found: " + colArr.length);
			return false;
		}
		for(String col: colArr){
			if(!(col != null && !col.trim().isEmpty())){
				logger.error("Column can't be empty.");
				return false;
			}
		}
		return true;
	}
}
