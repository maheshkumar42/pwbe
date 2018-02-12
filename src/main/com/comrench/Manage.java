package main.com.comrench;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.comrench.record.City;
import main.com.comrench.record.Province;
import main.com.comrench.record.School;

/**
 * Class: Manage
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to manage the IO operations for the application. It takes the input file name
 * from the Main class and then launches the instances of ReadInputFile and ProcessInputRecord classes so that data
 * can be read from input and processed respectively.
 * 
 * Date created: 20180211
 * 
 */

public class Manage {	
	private String inputFileName;
	private ReadInputFile readInputFile;
	private ProcessInputRecord processInputRecord;
	private Province province;
	private City city;
	private School school;
	private final static Logger logger = LogManager.getLogger(Manage.class);
	
	/**
	 * This is the parameterized constructor of the Manage class which takes input file name as parameter.
	 * @param String Input file name
	 */	
	
	public Manage(String inputFileName){
		this.inputFileName = inputFileName;
		logger.info("Input file name: "+inputFileName);
		processInputRecord = new ProcessInputRecord();
		readInputFile = new ReadInputFile(this);	
	}
	
	/**
	 * This method is called from Main to initiate the processing of the input file once configuation file has
	 * been read.  
	 * @param none
	 * @return boolean true if executed successfully else returns false
	 */
	
	public boolean manageFileIO(){
		if(readInputFile.processInputFile()){
			return true;
		}
		return false;
	}

	/**
	 * This method is called from Main to initiate the generation of the output file summarizing the population 
	 * per school, city and province.
	 * @param String Output file name
	 * @return boolean true if executed successfully else returns false
	 */
	
	public boolean generateOutputReport(String outputFileName){
		GenerateOutputFile generateOutputFile = new GenerateOutputFile(this,outputFileName);
		if(generateOutputFile.generateReport()){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This method is the getter for the input file name.
	 * @param none
	 * @return String Input file name
	 */
	
	public String getInputFileName() {
		return inputFileName;
	}

	/**
	 * This method is the getter for the instance of ProcessInputRecord.
	 * @param none
	 * @return ProcessInputRecord Instance of ProcessInputRecord class
	 */
	
	public ProcessInputRecord getProcessInputRecord() {
		return processInputRecord;
	}

	/**
	 * This method is the getter for the instance of Province.
	 * @param none
	 * @return Province Instance of Province class
	 */
	
	public Province getProvince() {
		return province;
	}

	/**
	 * This method is the getter for the instance of City.
	 * @param none
	 * @return City Instance of City class
	 */
	
	public City getCity() {
		return city;
	}

	/**
	 * This method is the getter for the instance of School.
	 * @param none
	 * @return School Instance of School class
	 */
	
	public School getSchool() {
		return school;
	}		
}
