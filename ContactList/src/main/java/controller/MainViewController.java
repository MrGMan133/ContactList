package controller;


import galekop.be.ContactList.MainApp;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import model.Person;
import persistency.PersonDAO;

public class MainViewController {
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
    public ObservableList<Person> getPersonData() {
        return personData;
    }
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
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
	    if (selectedIndex >= 0) {
	    	personListView.getItems().remove(selectedIndex);
	    } else {
	        // Nothing selected.
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setTitle("No Selection");
	        alert.setHeaderText("No Person Selected");
	        alert.setContentText("Please select a person in the table.");

	        alert.showAndWait();
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
    @FXML
    private void handleClose() {
    	Platform.exit();
    }
}
