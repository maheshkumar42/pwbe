package main.com.comrench;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.comrench.xml.FileDetails;

/**
 * Class: Main
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: This is the main class which drives the application.
 * Flow of application is as follows:
 * 1. Application takes the input argument for the config xml file.
 * 2. Parses the config xml file using the JAXB parser and then fetch the input xml file and output file name.
 * 3. Then it reads the input stats from the input file and starts processing one line at a time.
 * 4. For each record fetched from the input file, app validates if record is valid or not. 
 * 5. If record is invalid then it logs the message and ignores the record and moves to next records.
 * 6. If record is valid then app fetches the information of province, city, school, grade and population and processes it i.e.
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
 * 7. Once all the records are done then it creates the output file.
 * 8. Then updates the output file summarizing total population per school, city and province.
 * 
 * Date created: 20180211
 * 
 */

public class Main {

	private final static Logger logger = LogManager.getLogger(Main.class);
	private Manage manage;
	private FileDetails fileDetails;
	private String inputFileName;
	private String outputFileName;
	private boolean outputFileOverrideStatus;

	/**
	 * This is the main method which initiates the application by taking the arguments and passes it to execute method.
	 * @param args application arguments.
	 * @return none
	 * @exception none
	 */
	
	public static void main(String[] args) {
		logger.info("Starting the execution ... ");
		Main main = new Main();
		if(main.execute(args)){
			logger.info("Execution completed successfully.");			
		}else{
			logger.info("Errors during execution.");
		}	
	}

	/**
	 * This method takes parameters from the main method and then reads the input configuration file. Then it launches
	 * the processFile method so that it can use the data of the configuration file.
	 * @param args Input arguments received from main method.
	 * @return boolean true if executed successfully else returns false
	 * @exception none
	 */
	
	public boolean execute(String[] args) {
//		System.out.println(args.length);
		if(args.length==0 || args == null){
			logger.error("Input argument MISSING.");
			logger.error("Usage   : java -Dlog4j.configurationFile=File:<log4j2XmlFile> -jar <jarFile> <inputXmlFile>");
			logger.error("Example : java -Dlog4j.configurationFile=File:etc/log4j2.xml -jar pwbe.jar etc/input.xml");
			return false;
		}else{
			if(args[0]!=null){
				if(readConfigurationFile(args[0])){
					logger.debug("Configutation file read successfully.");
					logger.debug("Launching the processFile method.");
					if(!processFile()){
						logger.error("Unable to complete.");
						return false;
					}
				}else{
					logger.debug("Error while reading the confiration file.");
					return false;
				}					
			}else{
				logger.error("Argument cannot be null");
				return false;
			}
		}	
		return true;
	}

	/**
	 * This method reads the input configuration file which has names for input file name, output file details etc.
	 * @param String Configuration file name
	 * @return boolean true if executed successfully else returns false
	 */
	
	private boolean readConfigurationFile(String fileName) {
		try {
			logger.info("Loading the configuation XML file: "+fileName);
			JAXBContext jc = JAXBContext.newInstance(FileDetails.class);
			Unmarshaller unmarshaller = jc.createUnmarshaller();
			File xmlFile = new File(fileName.trim());
			if(xmlFile.exists()){
				fileDetails = (FileDetails)unmarshaller.unmarshal(xmlFile);
				if(fileDetails == null){
					logger.error("Error while parsing the input XML file");
					return false;
				}
				inputFileName = fileDetails.getInputFile().trim();
				outputFileName = fileDetails.getOutputFile().getName().trim();
				if(fileDetails.getOutputFile().getOverrideExistingFile().trim().equalsIgnoreCase("true")){
					outputFileOverrideStatus = true;
					outputFileName = outputFileName + ".txt";
				}else{
					outputFileOverrideStatus = false;
					outputFileName = outputFileName +"_"+ dateTime() + ".txt";
				}
				logger.info("Configuration XML loaded successfully.");
				logger.debug("Input file: "+inputFileName);
				logger.debug("Output file: "+ outputFileName);
				logger.debug("Output file override status: "+outputFileOverrideStatus);
			}else{
				logger.error("Input Configuration file not found at location: "+fileName);
				return false;
			}
		} catch (JAXBException|NullPointerException e) {
			logger.error("Unable to read parse input XML file.");
			logger.error(e.toString());
			return false;
		}
		return true;
	}

	/**
	 * This method finds the data & time timestamp which get appended to the name of the output file.
	 * @param none
	 * @return String Timestamp to be added to the output file name
	 */

	
	private String dateTime() {
		LocalTime localTime = LocalTime.now();
		LocalDate localDate = LocalDate.now();
		String dateTime = localDate.toString().replaceAll("-","")+localTime.getHour()+""+localTime.getMinute()+""+localTime.getSecond();
		logger.debug("dateTime is "+dateTime);
		return dateTime;
	}

	/**
	 * This method manages the IO of the input/output file. Once data has been read/processed from the config file then
	 * it calls the manageFileIO method of the manage class.
	 * @param none
	 * @return boolean true if executed successfully else returns false
	 */
	
	private boolean processFile(){
		manage = new Manage(inputFileName);
		if(!manage.manageFileIO())
			return false;
		manage.generateOutputReport(outputFileName);
		return true;
	}	

	/**
	 * This method is the getter for the output file name.
	 * @param none
	 * @return String Output file name as String
	 */
	
	public String getOutputFileName() {
		return outputFileName;
	}
}
