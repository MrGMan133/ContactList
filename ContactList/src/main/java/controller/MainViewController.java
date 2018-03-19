package controller;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import galekop.be.ContactList.MainApp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Person;
import persistency.PersonDAO;
import utilities.SortingUtility;

public class MainViewController {
	private static Logger log = Logger.getLogger(MainViewController.class.getName());
	private MainApp mainApp;
	private PersonDAO personDAO = new PersonDAO();
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
    	this.setListViewFromDb();
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
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    //populate listview from db
    private void setListViewFromDb() {
    	personListView.getItems().clear();
    	List<Person> source = personDAO.findAll();
    	for (Person person : source) {
			personData.add(person);
		}
    	personListView.setItems(personData);
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
 
    
    /**
     * Called when the user clicks the new button. Opens a dialog to edit
     * details for a new person.
     */
    @FXML
    private void handleNewPerson() {
        Person tempPerson = new Person();
        boolean okClicked = mainApp.showPersonEditDialog(tempPerson);
        if (okClicked) {
        	this.getPersonData().add(tempPerson);
        	personDAO.save(tempPerson);
        	log.info("Person: " + tempPerson.toString() + " saved.");
        }
    }

    /**
     * Called when the user clicks the edit button. Opens a dialog to edit
     * details for the selected person.
     */
    @FXML
    private void handleEditPerson() {
        Person selectedPerson = personListView.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = mainApp.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                showPersonDetails(selectedPerson);
                personDAO.update(selectedPerson);
                log.info("Person: " + selectedPerson.toString() + " updated.");
                this.setListViewFromDb();
            }

        } else {
            // Nothing selected.
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle("No Selection");
            alert.setHeaderText("No Person Selected");
            alert.setContentText("Please select a person in the table.");
            alert.showAndWait();
        }
    }
    //Handle the deletebutton
    @FXML
    private void handleDeletePerson() {
    	Person selectedPerson = personListView.getSelectionModel().getSelectedItem();
	    if (selectedPerson != null) {
	    	Alert alert = new Alert(AlertType.CONFIRMATION);
	    	alert.setTitle("Confirmation");
	    	alert.setHeaderText(null);
	    	alert.setContentText("Are you sure you want to delete?");
	    	Optional<ButtonType> action = alert.showAndWait();
	    	if (action.get() == ButtonType.OK) {
	    		personDAO.remove(selectedPerson);
		    	log.info("Person: " + selectedPerson.toString() + " removed.");
		    	personData.remove(selectedPerson);
			}
	    	
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
	    }
    }
    //Handle close
    @FXML
    private void handleClose() {
    	Platform.exit();
    }
    //Handle sort ascending
    @FXML
    private void handleSortAsc() {
    	SortingUtility.lastNameSort(personData);
    	log.info("button pushed");
    }
}
