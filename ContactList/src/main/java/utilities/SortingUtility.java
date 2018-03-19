package utilities;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import model.Person;

public class SortingUtility {
	private static Logger log = Logger.getLogger(SortingUtility.class.getName());
	//add log
	//firstnamesort hermaken
	//refresh list in mainviewcontroller

	public static void lastNameSort(ObservableList<Person> people) {
		Person p1;
		Person p2;
	    for (int a=1; a<people.size(); a++) {
	        for(int b=0; b<people.size() - a; b++) {
	        	p1 = people.get(b);
	        	p2 = people.get(b+1);
	        	if (((p1.getLastName()).compareTo((p2.getLastName()))) > 0)
	            {
	                //swap movies[b] with movies[b+1]
	                people.set(b, p2);
	                people.set(b + 1, p1);
	                log.info("Test " + p2.toString());
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
