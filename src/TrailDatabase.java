/**
 * Makes Trail Database and sorts it based on user input
 * @author ArmanAydemir
 * @version Feb 5, 2015
 * help from http://www.vogella.com/tutorials/JavaAlgorithmsMergesort/article.html
 */

import java.io.*;
import java.util.*;

public class TrailDatabase {
	ArrayList<Waypoint> helper;
	ArrayList<Waypoint> points;
	
	/**
	 * initializes TrailDatabse objects
	 */
	public TrailDatabase() {
		points = new ArrayList<Waypoint>();
		helper = new ArrayList<Waypoint>();
		populateDatabase();
	}

	/**
	 * populates the list points from the apptrailDB text file
	 */
	private void populateDatabase() {
		Scanner i = null;
		try {
			i = new Scanner(new File("apptrailDB.txt"));
			while(i.hasNextLine()){
				String[] te = i.nextLine().split("\t");
				Double lat = Double.parseDouble(te[3]);
				Double lon = Double.parseDouble(te[4]);
				Double dfs = Double.parseDouble(te[5]);
				Double dfk = Double.parseDouble(te[6]);
				int el = Integer.parseInt(te[7]);
				points.add(new Waypoint(te[0], te[1], te[2], lat, lon, dfs, dfk, el));
				helper.add(new Waypoint(te[0], te[1], te[2], lat, lon, dfs, dfk, el));
			}
		}catch(Exception e){}
	}
	
	/**
	 * sorts the Database
	 * @param wc
	 */
	public void sortDB(WaypointComparator wc)	{
		mergesort(0, points.size()-1, wc);
	}
	
	
	/**
	 * main
	 * @param args
	 */
	public static void main(String[] args) {
		ArrayList<String> terms = new ArrayList<String>(Arrays.asList("TY", "NA", "ST", "LA", "LO", "DS", "DK", "EL"));
		TrailDatabase r = new TrailDatabase();
		r.populateDatabase();
		while(true){
			String[] w = r.getSearchTerms();
			if(w == null) 
				break;
			else if(terms.contains(w[0]) && (w[1].equals("A") || (w[1].equals("D")))){
				r.sortDB(new WaypointComparator(w[1].equals("A"), w[0]));  
				r.printDatabase();}
			else
				System.err.println("Invalid Input");
		}
	}
	
	/**
	 * 
	 * @return array of sorting parameters
	 */
	public String[] getSearchTerms(){
		Scanner woah = new Scanner(System.in);
		System.out.println("*** Welcome to the Appalachian Trail Database ***");
		System.out.println("+ Menu of search terms to use for sorting waypoints +");
		System.out.println("	TY: by type"); 
		System.out.println("	NA: by name");
		System.out.println("	ST: by state");
		System.out.println("	LA: by latitude");
		System.out.println("	LO: by longitude");
		System.out.println("	DS: by distance to Springer");
		System.out.println("	DK: by distance to Katahdin");
		System.out.println("	EL: by elevation");
		System.out.print("Enter your preferred sort by term or 'Q' to quit: ");
		String term = woah.nextLine().toUpperCase();
		if(! term.equals("Q")){
			System.out.print("Enter 'A' to sort in ascending order or 'D' to sort in descending order: ");
			return new String[] {term, woah.nextLine().toUpperCase()};
		}
		return null; 
	}
	
	
	/**
	 * prints list points
	 */
	public void printDatabase() {
		for(Waypoint point: points) {
			System.out.println(point);
		}
	}
	
	/**
	 * merges
	 * @param l
	 * @param middle
	 * @param h
	 * @param wc
	 */
	private void merger(int l, int middle, int h, WaypointComparator wc){
		 // Copy both parts into the helper array
	    for (int i = l; i <= h; i++) {
	      helper.set(i, points.get(i));
	    }

	    int i = l;
	    int j = middle + 1;
	    int k = l;
	    // Copy the smallest values from either the left or the right side back
	    // to the original array
	    while (i <= middle && j <= h) {
	      if (wc.compare(helper.get(i), helper.get(j)) > 0) {
	        points.set(k, helper.get(i));
	        i++;
	      } else {
	    	  points.set(k, helper.get(j));
	        j++;
	      }
	      k++;
	    }
	    // Copy the rest of the left side of the array into the target array
	    while (i <= middle) {
	      points.set(k, helper.get(i));
	      k++;
	      i++;
	    }

	 }
	
	/**
	 * Separates the lists for the merge sort algorithm
	 * @param l
	 * @param h
	 * @param wc
	 */
	private void mergesort(int l, int h, WaypointComparator wc){
		if (l < h) {
			
		      int middle = l + (h - l) / 2;
		      
		      mergesort(l, middle, wc);
		      
		      mergesort(middle + 1, h, wc);
		      
		      merger(l, middle, h, wc);
		    }
	}
}
