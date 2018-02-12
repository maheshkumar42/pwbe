package main.com.comrench;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import main.com.comrench.record.Place;

/**
 * Class: GenerateOutputFile
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to extract the data from objects i.e. population per school, city and province.
 * Then it generates a output report summarizing those details.
 * 
 * Date created: 20180211
 * 
 */


public class GenerateOutputFile {

	private Map<String,Place> provinceMap;
	private int uniqueCounter = 1000000;	
	private Map<String,String> finalResult;
	private String outputFileName;
	private final static Logger logger = LogManager.getLogger(GenerateOutputFile.class);
	
	/**
	 * This is the parameterized constructor of the GenerateOutputFile class.
	 * @param Manage Instance of Manage class
	 * @param String Output file name as String
	 */
	
	public GenerateOutputFile(Manage manage,String outputFileName) {
		this.outputFileName = outputFileName;
		provinceMap = manage.getProcessInputRecord().getListOfPlaces();
		finalResult = new HashMap<>();
	}
	
	/**
	 * This method fetches the information from the Maps and then calls the printReport method to generate the report
	 * summarizing the population per school, city and province.
	 * @param none
	 * @return boolean true if executed successfully else returns false
	 */
	
	public boolean generateReport(){
		logger.info("Generating the report.");
		provinceMap.forEach((keyProvince, placeProvince) -> {
			placeProvince.getListOfPlaces().forEach((keyCity,placeCity)->{
				placeCity.getListOfPlaces().forEach((keySchool,placeSchool)->{
					insertIntoResultMap(placeSchool.getNumOfPeople(),keySchool);
				});
				insertIntoResultMap(placeCity.getNumOfPeople(),keyCity);
			});
			insertIntoResultMap(placeProvince.getNumOfPeople(),keyProvince);
		});
        if(!printReport()){
        	return false;
        }
        return true;
	}
	
	/**
	 * This method creates the output file summarizing the population per school, city and province.  
	 * @param none
	 * @return boolean true if executed successfully else returns false
	 */
	
	private boolean printReport() {
		try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(new File(outputFileName)))){
			Map<String, String> treeMap = new TreeMap<String, String>(finalResult);
			for(String line:treeMap.values()){
				logger.info(line);
				bufferedWriter.write(line.trim()+"\n");
			}
			return true;
		}catch(IOException e){
			e.printStackTrace();
			logger.error("Error while writing in "+outputFileName);
			return false;
		}
	}

	/**
	 * This method inserts the data in the finalResult Map in appropriate format which is then used for generating 
	 * the output file summarizing the population per school, city and province.  
	 * @param int Number of People
	 * @param String Key of the HashMap i.e. province, city or school
	 * @return none
	 */
	
	private void insertIntoResultMap(int numOfPeople, String resultKey) {
		String result = String.format("%-20s %-15s",resultKey,numOfPeople);
		String key = createUniqueKey(String.valueOf(numOfPeople));
		finalResult.put(key, result);
	}

	/**
	 * This method creates the unique key so that duplicate data can be inserted into the finalResult Map in 
	 * appropriate format which is then used for generating the output file summarizing the population per 
	 * school, city and province.  
	 * @param String Takes the population as key
	 * @return String Returns the unique string by adding the prefix and suffix.
	 */
	
	private String createUniqueKey(String key) {
		int len = key.length();
		uniqueCounter++;
		String prefix = "";
		switch(len){
			case 1: prefix = "0000000000";
				break;
			case 2: prefix = "000000000";
				break;
			case 3: prefix = "00000000";
				break;
			case 4: prefix = "0000000";
				break;
			case 5: prefix = "000000";
				break;
			case 6: prefix = "00000";
				break;
			case 7: prefix = "0000";
				break;
			case 8: prefix = "000";
				break;
			case 9: prefix = "00";
				break;
			case 10: prefix = "0";
				break;
			default:
				System.out.println("Popultation more than billion");
		}
		return prefix+key+uniqueCounter;
	}
}
