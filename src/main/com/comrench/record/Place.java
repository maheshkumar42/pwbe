package main.com.comrench.record;

import java.util.Map;

/**
 * Interface: Place
 * 
 * @author Mahesh Kumar
 * 
 * Purpose: Purpose of this is to provide the blue print for places like province, city and school.
 * 
 * Date created: 20180211
 * 
 */

public interface Place {
	public String getPlaceName();
	public int getNumOfPeople();
	public Map<String, Place> getListOfPlaces();
	public void addPeople(int numOfPeople);
}
