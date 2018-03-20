package utilities;




import javafx.collections.ObservableList;
import model.Person;

public class SortingUtility {
	//add log
	//firstnamesort hermaken
	//refresh list in mainviewcontroller

	public static void lastNameSort(ObservableList<Person> people) {
		Person person1;
		Person person2;
	    for (int a=1; a<people.size(); a++) {
	        for(int b=0; b<people.size() - a; b++) {
	        	person1 = people.get(b);
	        	person2 = people.get(b+1);
	        	if (((person1.getLastName()).compareToIgnoreCase((person2.getLastName()))) > 0)
	            {
	                people.set(b, person2);
	                people.set(b + 1, person1);
	            }
	        }
	    }
	}
	public static void firstNameSort(ObservableList<Person> people) {
		Person person1;
		Person person2;
	    for (int a=1; a<people.size(); a++) {
	        for(int b=0; b<people.size() - a; b++) {
	        	person1 = people.get(b);
	        	person2 = people.get(b+1);
	        	if (((person1.getFirstName()).compareToIgnoreCase((person2.getFirstName()))) > 0)
	            {
	        		people.set(b, person2);
	        		people.set(b + 1, person1);
	            }
	        }
	    }
	}	
}
