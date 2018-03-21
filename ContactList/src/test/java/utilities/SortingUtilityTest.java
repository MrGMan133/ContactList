package utilities;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import junit.framework.TestCase;
import model.Person;

public class SortingUtilityTest extends TestCase {
	private ObservableList<Person> testList;
	private ObservableList<Person> expectedListFirstName;
	private ObservableList<Person> expectedListLastName;

	protected void setUp() throws Exception {
		Person person1 = new Person("Alpha", "Bravo");
		Person person2 = new Person("Bravo", "Alpha");
		testList = FXCollections.observableArrayList(person1, person2);
		expectedListFirstName = FXCollections.observableArrayList(person1, person2);
		expectedListLastName = FXCollections.observableArrayList(person2, person1);
	}

	public void testLastNameSort() {
		SortingUtility.lastNameSort(testList);
		assertEquals(expectedListLastName, testList);
	}

	public void testFirstNameSort() {
		SortingUtility.firstNameSort(testList);
		assertEquals(expectedListFirstName, testList);
	}

}
