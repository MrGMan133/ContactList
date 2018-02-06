package controller;

import com.sun.javafx.fxml.LoadListener;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import model.Person;

public class MainViewController {
	@FXML
	private ListView<Person> personListView;
    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label mailLabel;
    @FXML
    private Label phoneLabel;
	
    private ObservableList<Person> personData = FXCollections.observableArrayList();
    
    public void initialize() {
    	personData.add(new Person("Hans", "Muster"));
        personData.add(new Person("Ruth", "Mueller"));
        personData.add(new Person("Heinz", "Kurz"));
        personData.add(new Person("Cornelia", "Meier"));
        personData.add(new Person("Werner", "Meyer"));
        personData.add(new Person("Lydia", "Kunz"));
        personData.add(new Person("Anna", "Best"));
        personData.add(new Person("Stefan", "Meier"));
        personData.add(new Person("Martin", "Mueller"));
        personListView.setItems(personData);
        //Selected item listener
        personListView.getSelectionModel().selectedItemProperty().addListener(
        		new ChangeListener<Person>() {
        			@Override
        			public void changed(ObservableValue<? extends Person> ov,
        					Person oldValue, Person newValue) {
        				showPersonDetails(newValue);
        			}
				});
        
    }
    //Show the content in the details pane
    private void showPersonDetails(Person person) {
        if (person != null) {
            // Fill the labels with info from the person object.
            firstNameLabel.setText(person.getFirstName());
            lastNameLabel.setText(person.getLastName());
            mailLabel.setText(person.geteMail());
            phoneLabel.setText(person.getPhoneNumber());
        } else {
            // Person is null, remove all the text.
            firstNameLabel.setText("");
            lastNameLabel.setText("");
            mailLabel.setText("");
            phoneLabel.setText("");
        }
    }
    //Handle the deletebutton
    @FXML
    private void handleDeletePerson() {
        int selectedIndex = personListView.getSelectionModel().getSelectedIndex();
        personListView.getItems().remove(selectedIndex);
    }
}
