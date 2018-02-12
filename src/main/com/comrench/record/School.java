package main.com.comrench.record;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class: School
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to process the input record details. It takes input from the city and then updates
 * the number of people with brown eyes for that school.
 * 
 * This class implements Place interface.
 * 
 * Date created: 20180211
 * 
 */

public class School implements Place {

	private Map<String,Place> listOfPlaces;
	private int numOfPeople;
	private String schoolName;
	private final static Logger logger = LogManager.getLogger(School.class);
	
	/**
	 * This is the parameterized constructor of the School class which takes number of people as parameter.
	 * @param int Number of people
	 */	
	
	public School(int numOfPeople) {
		this.numOfPeople = numOfPeople;
	}

	/**
	 * This method is the getter for the place name.
	 * @param none
	 * @return String Name of school
	 */
	
	@Override
	public String getPlaceName() {
		return schoolName;
	}
	
	/**
	 * This method is the getter for the number of people in province.
	 * @param none
	 * @return int Number of people
	 */
	
	@Override
	public int getNumOfPeople() {
		return numOfPeople;
	}
	
	/**
	 * This method is the getter for the list of places.
	 * @param none
	 * @return Map<String,Place> List of places in the Map
	 */
	
	@Override
	public Map<String, Place> getListOfPlaces() {
		return listOfPlaces;
	}	

	/**
	 * This method adds the number of people to the existing number of people for province
	 * @param int Number of people
	 * @return void
	 */
	
	@Override
	public void addPeople(int numOfPeople) {
		this.numOfPeople += numOfPeople;
	}
}
