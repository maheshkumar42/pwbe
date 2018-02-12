package main.com.comrench;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class: ReadInputFile
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to read the input file line by line and send those lines to ProcessInputRecord class's
 * processRecord method so that line record and be validated and then processed if it is valid.
 * 
 * Date created: 20180211
 * 
 */

public class ReadInputFile {
	
	private String inputFileName;
	private ProcessInputRecord processInputRecord;
	private final static Logger logger = LogManager.getLogger(ReadInputFile.class);
	
	/**
	 * This is the parameterized constructor of the ReadInputFile class which takes manage instance as parameter.
	 * @param Manage Instance of Manage class
	 */	
	
	public ReadInputFile(Manage manage){
		inputFileName = manage.getInputFileName();
		processInputRecord = manage.getProcessInputRecord();
	}
	
	/**
	 * This method is to process the input file whose name is fetched from the configuration file. Once file is read
	 * then records are sent for validation.
	 * @param none
	 * @return boolean true if executed successfully else returns false
	 */
	
	public boolean processInputFile(){
		logger.info("Processing the statistics of people from province, city & school.");
		try (BufferedReader bufferedReader = new BufferedReader(new FileReader(new File(inputFileName)))) {
			String line;
			while ((line = bufferedReader.readLine()) != null) {
				processInputRecord.processRecord(line);
			}
		} catch (IOException e) {
			logger.error("Exception while processing the file");
			logger.error(e.toString());
			return false;
		}
		return true;
	}
}
