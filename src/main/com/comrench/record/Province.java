package main.com.comrench.record;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Class: Province
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this class is to process the input record details. It takes input from the ProcessInputRecord 
 * and then checks if city is already there or not.
 * Check if City already exists in the province 
 *			a).	if yes add the population to existing city of that province.
 *			b).	if no create the new city in the province and add the population to the new city of that province.
 * 
 * This class implements Place interface.
 * 
 * Date created: 20180211
 * 
 */

public class Province implements Place {

	private String provinceName;
	private int numOfPeople;
	private Place place;
	private Map<String,Place> listOfPlaces;
	private final static Logger logger = LogManager.getLogger(Province.class);
	
	/**
	 * This is the parameterized constructor of the Province class which takes province, city, school & number 
	 * of people as parameter.
	 * @param String Name of province
	 * @param String Name of city
	 * @param String Name of school
	 * @param int Number of people
	 */	
	
	public Province(String province, String city, String school, int numOfPeople) {		
		listOfPlaces = new HashMap<>();
		provinceName = province;
		this.numOfPeople = numOfPeople;
		if(listOfPlaces.containsKey(city)){
			System.out.println("Already exists "+city);
		}else{
			place = new City(school,numOfPeople);
			listOfPlaces.put(city,place);
		}	
	}

	/**
	 * This method is the getter for the place name.
	 * @param none
	 * @return String Name of province
	 */
	
	@Override
	public String getPlaceName() {
		return provinceName;
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
	 * @return none
	 */
	
	@Override
	public void addPeople(int numOfPeople) {
		this.numOfPeople += numOfPeople;
	}

	
}
