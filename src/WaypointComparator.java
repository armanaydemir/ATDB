/**
 * Class used to compare Waypoint objects to sort them
 * @author ArmanAydemir
 * @version Feb 6, 2015
 */


import java.util.Comparator;


public class WaypointComparator implements Comparator<Waypoint> {
	String param = null;
	boolean asc = true;	
	
	/**
	 * Initializes WaypointComparator objects if no parameters are given (defaults)
	 */
	public WaypointComparator(){
		param = "DS";
		asc = true; 
	}
	
	/**
	 * initializes WaypointComparator objects
	 * @param a
	 * @param sort
	 */
	public WaypointComparator(boolean a, String sort) {
		param = sort;
		asc = a;
	}
	
	
	/**
	 * compares two waypoints based on given term
	 */
	public int compare(Waypoint w1, Waypoint w2) {
		
		if(param.equals("TY")){
			String l1 = w1.getType();
			String l2 = w2.getType();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("NA")){
			String l1 = w1.getName();
			String l2 = w2.getName();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("ST")){
			String l1 = w1.getState();
			String l2 = w2.getState();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("LA")){
			Double l1 = w1.getLatitude();
			Double l2 = w2.getLatitude();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("LO")){ 
			Double l1 = w1.getLongitude();
			Double l2 = w2.getLongitude();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("DS")){
			Double l1 = w1.milesToSpringer();
			Double l2 = w2.milesToSpringer();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("DK")){
			Double l1 = w1.milesToKatahdin();
			Double l2 = w2.milesToKatahdin();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else if(param.equals("EL")){
			Integer l1 = w1.getElevation();
			Integer l2 = w2.getElevation();
			return ! asc ? l1.compareTo(l2) : l1.compareTo(l2) * -1;}
		else
			return 0;
		
		
			
	}

	
	
}
