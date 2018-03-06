package galekop.be.ContactList;

import java.io.IOException;

import controller.MainViewController;
import controller.PersonEditController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.Person;

public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainWindow.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
            // Show the scene containing the root layout.
            Scene scene = new Scene(page);
            primaryStage.setScene(scene);
            primaryStage.show();
			// Give the controller access to the main app.
	        MainViewController controller = loader.getController();
	        controller.setMainApp(this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		launch(args);
	}
	public boolean showPersonEditDialog(Person person) {
	    try {
	        // Load the fxml file and create a new stage for the popup dialog.
	        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/EditAddPerson.fxml"));
	        AnchorPane page = (AnchorPane) loader.load();
	        // Create the dialog Stage.
	        Stage dialogStage = new Stage();
	        dialogStage.setTitle("Edit Person");
	        dialogStage.initModality(Modality.WINDOW_MODAL);
	        dialogStage.initOwner(primaryStage);
	        Scene scene = new Scene(page);
	        dialogStage.setScene(scene);
	        // Set the person into the controller.
	        PersonEditController controller = loader.getController();
	        controller.setDialogStage(dialogStage);
	        controller.setPerson(person);
	        // Show the dialog and wait until the user closes it
	        dialogStage.showAndWait();
	        return controller.isOkClicked();
	    } catch (IOException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}
