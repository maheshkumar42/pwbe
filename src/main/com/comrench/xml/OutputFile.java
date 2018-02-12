package main.com.comrench.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * Class: OutputFile
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is help in fetching the information from the configuration XML file. It fetches name 
 * of the output file and the override status which means that if override status is true then output file replaces 
 * the existing file but if it is set to false then it attaches the date time stamp on the file name.
 * 
 * Date created: 20180211
 * 
 */

@XmlType
public class OutputFile {
	String name;
	String overrideExistingFile;

	/**
	 * This method is the getter for the name of output file.
	 * @param none
	 * @return String Name of the output file
	 */
	
	@XmlElement(name="name")
	public String getName() {
		return name;
	}

	/**
	 * This method is the setter to the name of output file.
	 * @param String Name of the output file
	 * @return none
	 */
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * This method is the getter for the override status of output file.
	 * @param none
	 * @return String Override status for output file
	 */
	
	@XmlElement(name="overrideExistingFile")
	public String getOverrideExistingFile() {
		return overrideExistingFile;
	}

	/**
	 * This method is the setter to the override status of output file.
	 * @param String Override status for output file
	 * @return none
	 */
	
	public void setOverrideExistingFile(String overrideExistingFile) {
		this.overrideExistingFile = overrideExistingFile;
	}
}
