package hysteresis;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.omg.CORBA.Environment;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Controller {

	@FXML
	public Button fileButton;
	@FXML
	public Button executeButton;
	@FXML
	public Button resetButton;
	@FXML
	public Button addSegButtons;
	@FXML
	public TextArea output;
	@FXML
	public TextField num1;
	@FXML
	public TextField num2;
	@FXML
	public Label folderLabel;

	public DirectoryChooser fileChooser = new DirectoryChooser();
	private String outStr = "";
	private File folder;
	public File[] listOfFiles;
	public ArrayList<Segments> segs = new ArrayList<Segments>();
	public Hysteresis hys;

	@FXML
	protected void addSegs() {
		outStr = "";

		segs.add(new Segments(Integer.parseInt(num1.getText()), Integer.parseInt(num2.getText())));

		for (int i = 0; i < segs.size(); i++) {
			outStr += "New segment added" + segs.get(i).getNum1() + ", " + segs.get(i).getNum2() + "\n";
		}
		output.setText(outStr);

	}

	@FXML
	protected void resetApp() {
		outStr = "";
		output.setText(outStr);
		folder = null;
		listOfFiles = null;

	}

	@FXML
	protected void initialize() {
		executeButton.setDisable(true);
	}

	@FXML
	protected void fileButtonClicked() {
		outStr = "";

		fileChooser.setTitle("Select Directory");
		folder = fileChooser.showDialog(null);

		// outStr += "Folder: " + folder + "\n";
		folderLabel.setText("Folder: " + folder);

		listOfFiles = folder.listFiles();

		outStr += listOfFiles.length + " Files found:\n";

		for (int i = 0; i < listOfFiles.length; i++) {
			outStr += "   " + listOfFiles[i].getName() + "\n";
		}

		output.setText(outStr);

		executeButton.setDisable(false);

	}

	@FXML
	protected void execute() throws IOException {
		outStr = "";

		for (int i = 0; i < listOfFiles.length; i++) {

			String inputFile = listOfFiles[i].toString();

			// for (int j = 0; i < segs.size(); i++) {
			// hys = new Hysteresis(inputFile, segs.get(j).getNum1(),
			// segs.get(j).getNum2());
			// }

			hys = new Hysteresis(inputFile, 8, 9);
			hys = new Hysteresis(inputFile, 18, 19);
			hys = new Hysteresis(inputFile, 28, 29);
			hys = new Hysteresis(inputFile, 38, 39);
			hys = new Hysteresis(inputFile, 48, 49);

			outStr += listOfFiles[i].getName() + " is done\n";
			output.setText(outStr);
		}

		outStr = "Done processing\n";
		output.setText(outStr);
	}

}
