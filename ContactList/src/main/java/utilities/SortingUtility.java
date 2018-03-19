package utilities;

import java.util.logging.Logger;


import model.Person;

public class SortingUtility {
	private static Logger log = Logger.getLogger(SortingUtility.class.getName());
	//add return
	//add log
	public static void lastNameSort(Person[] people) {
	    for (int a=1; a<people.length; a++) {
	        for(int b=0; b<people.length - a; b++) {
	        	if (((people[b].getLastName()).compareTo((people[b+1].getLastName()))) > 0)
	            {
	                //swap movies[b] with movies[b+1]
	                Person temp = people[b];
	                people[b] = people[b+1];
	                people[b+1] = temp;
	                log.info(temp.toString());
	            }
	        }
	    }
	}
	public static void firstNameSort(Person[] people) {
	    for (int a=1; a<people.length; a++) {
	        for(int b=0; b<people.length - a; b++) {
	        	if (((people[b].getFirstName()).compareTo((people[b+1].getFirstName()))) > 0)
	            {
	                //swap movies[b] with movies[b+1]
	                Person temp = people[b];
	                people[b] = people[b+1];
	                people[b+1] = temp;
	            }
	        }
	    }
	}	
}
