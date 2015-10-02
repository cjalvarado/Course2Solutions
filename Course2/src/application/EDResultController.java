package application;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class EDResultController {
	
	private Stage dialogStage;
	//private MainApp mainApp;
	
	
	@FXML
	private Label pathLabel;
	
	@FXML
	private Label numStepsLabel;
	
	@FXML
	private Button okButton;
    
	
	@FXML
	private void initialize() {
		okButton.setDefaultButton(true);
	}
	
	/**
     * Sets the stage of this dialog.
     * @param dialogStage
     */
    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }
    
    /**
     * Sets the labels to corresponsing results
     * 
     * @param result - list of strings in path
     */
    public void setResult(List<String> result) {
    	if(result != null) {
	    	numStepsLabel.setText(Integer.toString(result.size()-1));
	    	
	    	String str = buildResultString(result);
	    	pathLabel.setText(str);
    	}
    	// no path found
    	else {
    		pathLabel.setText("No Path Found.");
    		numStepsLabel.setText("N/A");
    	}
    	
    }
    
    /**
     * Builds string representation of result
     * 
     * @param result - list of strings in path
     * @return single string with full path
     */
    private String buildResultString(List<String> result) {
    	return String.join( " -> ", result);
    }

    
    /**
     * Called when the user clicks ok.
     */
    @FXML
    private void handleOk() {
    	dialogStage.close();
    }
    
}