package main.com.comrench.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Class: FileDetails
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is help in fetching the information from the configation xml file. It fetches input
 * file name, output file node from the xml file.
 * 
 * Date created: 20180211
 * 
 */

@XmlRootElement(name="fileDetails")
public class FileDetails {
	String inputFile;
	OutputFile outputFile;

	/**
	 * This method is the getter for the input file from xml.
	 * @param none
	 * @return String Input file name
	 */
	
	@XmlElement(name="inputFile")	
	public String getInputFile() {
		return inputFile;
	}

	/**
	 * This method is the setter to the input file in xml.
	 * @param String Input file name
	 * @return none
	 */
	
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}

	/**
	 * This method is the getter for the output file details from xml.
	 * @param none
	 * @return OutputFile Instance of OutputFile class
	 */
	
	@XmlElement(name="outputFile")
	public OutputFile getOutputFile() {
		return outputFile;
	}	
	
	/**
	 * This method is the setter to the output file in xml.
	 * @param OutputFile Instance of OutputFile class
	 * @return none
	 */
	
	public void setOutputFile(OutputFile outputFile) {
		this.outputFile = outputFile;
	}
}
