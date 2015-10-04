package application;
	
import java.io.IOException;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;





public class MainApp extends Application {
	private Stage primaryStage;
	private BorderPane rootLayout;
	
	private LaunchClass launch;
	spelling.WordPath wp;
	textgen.MarkovTextGenerator mtg;
	
	
	
	// called at start of application
	@Override
	public void start(Stage primaryStage) {
		
		this.primaryStage = primaryStage;
		
		this.primaryStage.setTitle("TextProApp");
		//primaryStage.setResizable(false);
		
		try {
			// Load root layout from fxml
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/RootLayout.fxml"));
			rootLayout = (BorderPane) loader.load();
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            primaryStage.setMinHeight(430);
            primaryStage.setMinWidth(530);
            primaryStage.show();
            
            
            rootLayout.widthProperty().addListener( e -> {
            	System.out.println(rootLayout.getWidth());
            });
            
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		launch = new LaunchClass();
		
		showTextProApp();
	}
	
	/**
     * Shows the main TextApplication scene
     */
    public void showTextProApp() {
        try {
            // Load the fxml file and set into the center of the main layout
            FXMLLoader loader = new FXMLLoader(getClass().getResource("view/TextAppLayout.fxml"));
            
            HBox textProPage = (HBox) loader.load();
            rootLayout.setCenter(textProPage);
            
            // Connect controller and main app
            TextProController controller = loader.getController();
            controller.setMainApp(this);
            
        } catch (IOException e) {
            // Exception gets thrown if the fxml file could not be loaded
            e.printStackTrace();
        }
    }
    
    /**
     * Shows dialog for user input error
     * 
     * @param inErr - message to dispaly
     */
    public void showInputErrorDialog(String inErr) {
    	Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Input Error");
		alert.setContentText(inErr);

		alert.showAndWait();
    }
    
    /**
     * Displays dialog that allows user to select local text file to display in TextArea
     * 
     * @param ta - reference to TextArea to display loaded text file
     * 
     */
    public void showLoadFileDialog(AutoSpellingTextArea ta) {
    	try {
    		// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/LoadFileLayout.fxml"));
			VBox page = (VBox) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Load File");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			LoadFileDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			
			// give controller reference to text area to load file into
			controller.setTextArea(ta);

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
		    
		    
		

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    	
    }
    
 // not sure about parameter
    public void showEditDistanceDialog(String selectedText) {
    	try {
    		// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/EditDistanceLayout.fxml"));
			AnchorPane page = (AnchorPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Calculate Edit Distance");
			dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			EditDistanceDialogController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setField(selectedText);
			
			
			// give controller reference to scene (cursor)

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
		    
		    
		

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    	
    }
    
    //TODO
    public void showEDResult(List<String> path) {
    	try {
    		// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/EDResultLayout.fxml"));
			VBox page = (VBox) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Edit Distance Result");
			//dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			//BUG -- when first displayed results don't show up until resize window
			//EDResultController controller = new EDResultController();
			
			EDResultController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			//controller.setMainApp(this);
			
			// give controller reference to result
			controller.setResult(path);
			
			//loader.setController(controller);
			

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
		    
		    
		

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    }
    
    
    public void showMarkovDialog(textgen.MarkovTextGenerator mtg) {
    	try {
    		// Load the fxml file and create a new stage for the popup
			FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("view/MarkovLayout.fxml"));
			BorderPane page = (BorderPane) loader.load();
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Markov Text Generator");
			//dialogStage.initModality(Modality.WINDOW_MODAL);
			dialogStage.initOwner(primaryStage);
			Scene scene = new Scene(page);
			dialogStage.setScene(scene);

			// Set reference to stage in controller
			//BUG -- when first displayed results don't show up until resize window
			MarkovController controller = loader.getController();
			//controller.setDialogStage(dialogStage);
			controller.setMainApp(this);
			controller.setMTG(mtg);

			// Show the dialog and wait until the user closes it
		    dialogStage.showAndWait();
		    
		    
		

    	} catch (IOException e) {
    		// Exception gets thrown if the fxml file could not be loaded
    		e.printStackTrace();
    	}
    	
    	
    }
    
    public void showLoadStage(Stage loadStage, String text) {
    	loadStage.initModality(Modality.APPLICATION_MODAL);
    	loadStage.initOwner(primaryStage);
        VBox loadVBox = new VBox(20);
        loadVBox.setAlignment(Pos.CENTER);
        Text tNode = new Text(text);
        loadVBox.getChildren().add(new HBox());
        loadVBox.getChildren().add(tNode);
        loadVBox.getChildren().add(new HBox());
        Scene loadScene = new Scene(loadVBox, 300, 200);
        loadStage.setScene(loadScene);
        loadStage.show();
    }
    
    
    // getters for new objects needed
    public document.Document getDocument(String text) {
    	return launch.getDocument(text);
    }
    
    public textgen.MarkovTextGenerator getMTG() {
    	if(this.mtg != null) {
    		return this.mtg;
    	}
    	return this.mtg = launch.getMTG();
    }
    
    public spelling.WordPath getWordPath() {
    	if(wp != null) {
    		return this.wp;
    	}
    	else {
    		return this.wp = launch.getWordPath();
    	}
    }
    
    public spelling.AutoComplete getAutoComplete() {
    	return launch.getAutoComplete();
    }
    
    public spelling.Dictionary getDictionary() {
    	return launch.getDictionary();
    }
    
    public spelling.SpellingSuggest getSpellingSuggest(spelling.Dictionary dic) {
    	return launch.getSpellingSuggest(dic);
    }
	public static void main(String[] args) {
		launch(args);
	}
	
	public Stage getStage() {
		return this.primaryStage;
	}
	
}