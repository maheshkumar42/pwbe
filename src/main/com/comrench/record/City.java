package main.com.comrench.record;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import main.com.comrench.Manage;

/**
 * Class: City
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to process the input record details. It takes input from the Province and then 
 * checks if school is already there or not.
 * Check if School already exists in the City of the province 
 * 			a).	if yes add the population to existing School of that city for that province.
 *			b).	if no create the new School in the city of that province and add the population to the new School of 
 *			that city for that province.
 * 
 * This class implements Place interface.
 * 
 * Date created: 20180211
 * 
 */

public class City implements Place {

	private String cityName;
	private Place place;
	private Map<String,Place> listOfPlaces;
	private int numOfPeople;
	private final static Logger logger = LogManager.getLogger(City.class);
	
	/**
	 * This is the parameterized constructor of the City class which takes school & number of people as parameter.
	 * @param String School name as String
	 * @param int Number of people
	 */	
	
	public City(String school, int numOfPeople) {
		listOfPlaces = new HashMap<>();
		this.numOfPeople = numOfPeople;
		if(listOfPlaces.containsKey(school)){
			
		}else{
			place = new School(numOfPeople);
			listOfPlaces.put(school,place);
		}	
	}

	/**
	 * This method is the getter for the place name.
	 * @param none
	 * @return String City name as String
	 */
	
	@Override
	public String getPlaceName() {
		return cityName;
	}
	
	/**
	 * This method is the getter for the number of people in city.
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
	 * This method adds the number of people to the existing number of people for city
	 * @param int Number of people
	 * @return none
	 */
	
	@Override
	public void addPeople(int numOfPeople) {
		this.numOfPeople += numOfPeople;
	}
}
