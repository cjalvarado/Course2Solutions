package application;

import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class TextProController {

	private MainApp mainApp;
	
	// UI Controls
	private AutoSpellingTextArea textBox;
	
	@FXML
	private AnchorPane leftPane;
	
	@FXML
	private TextField fleschField;
	
	@FXML
	private CheckBox autocompleteBox;
	
	@FXML 
	private CheckBox spellingBox;
	
	
	
	 /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
	@FXML
	private void initialize() {
		// make field displaying flesch score read-only
		fleschField.setEditable(false);
		
		// instantiate and add custom text area
		textBox = new AutoSpellingTextArea();
		textBox.setPrefSize(570, 492);
		textBox.setWrapText(true);
		textBox.setLayoutX(40);
		textBox.setLayoutY(24);	
		leftPane.getChildren().add(textBox);
		
	}
	
	
	
	
	/**
     * Is called by the main application to give a reference back to itself.
     * Also give reference to AutoSpellingTextArea
     * 
     * 
     * @param mainApp
     */
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
		textBox.setMainApp(mainApp);
		textBox.setReferences();
	}
	
	@FXML
	private void handleFleschIndex() {
		String text = textBox.getText();
		double fIndex = 0;
		
		// check if text input
		if(!text.equals("")) {
			
			// how to instantiate??
			document.Document doc = mainApp.getDocument(text);
			
			fIndex = doc.getFleschScore();
			
			//PromptText?
			//get string with two decimal places for index to
			String fString = String.format("%.2f", fIndex);
			
			// display string in text field
			fleschField.setText(fString);
			
		}
		else {
			// reset text field
			fleschField.setText("");
			mainApp.showInputErrorDialog("No text entered.");
			
		}
		
	}
	

	
	@FXML
	private void handleLoadText() {
		//return string??
		mainApp.showLoadFileDialog(textBox);
		
		
		//textBox.appendText(text);
		
		
	}
	
	@FXML
	private void handleEditDistance() {
		String selectedText = textBox.getSelectedText();
		mainApp.showEditDistanceDialog(selectedText);
		
	}
	
	@FXML
	private void handleMarkovText() {
		// get MTG object
		textgen.MarkovTextGenerator mtg = mainApp.getMTG();
		
		Task<textgen.MarkovTextGenerator> task = new Task<textgen.MarkovTextGenerator>() {
	        @Override
	        public textgen.MarkovTextGenerator call() {
	            // process long-running computation, data retrieval, etc...

	            mtg.retrain(textBox.getText());
	            return mtg;
	        }
		};
		
		// stage for load dialog
		final Stage loadStage = new Stage();
		
		// consume close request until task is finished
		loadStage.setOnCloseRequest( e -> {
			if(!task.isDone()) {
				e.consume();
			}
		});

		
		// show loading dialog when task is running
		task.setOnRunning( e -> {
			mainApp.showLoadStage(loadStage, "Training MTG...");
		});
		
		// MTG trained, close loading dialog, show MTG dialog
	    task.setOnSucceeded(e -> {
	    	loadStage.close();
	        textgen.MarkovTextGenerator result = task.getValue();
	        mainApp.showMarkovDialog(result);
	    });
	    
	   Thread thread  = new Thread(task);
	   thread.start();
	  
		
		
	}
	
	
	
	@FXML
	private void handleAutoComplete() {
		if(autocompleteBox.isSelected()) {
			textBox.setAutoComplete(true);
		}
		else {
			textBox.setAutoComplete(false);
		}
	}
	
	@FXML
	private void handleSpelling() {
		if(spellingBox.isSelected()) {
			textBox.setSpelling(true);
		}
		else {
			textBox.setSpelling(false);
		}
		
	}
	
	
	@FXML
	private void handleClear() {
		textBox.clear();
	}
	
	
}
