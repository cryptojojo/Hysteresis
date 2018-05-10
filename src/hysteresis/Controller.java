package hysteresis;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

import org.omg.CORBA.Environment;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Controller {

	@FXML
	public Button fileButton;

	public DirectoryChooser fileChooser = new DirectoryChooser();

	private Desktop desktop = Desktop.getDesktop();

	@FXML
	protected void initialize() {

	}

	@FXML
	protected void fileButtonClicked() {

		fileChooser.setTitle("Select Directory");
		File ans = fileChooser.showDialog(null);

		System.out.println(ans);

	}

	@FXML
	protected void execute() throws IOException {

		String folderPath = "C:/Users/Joe/Desktop/gtr/";

		File folder = new File(folderPath);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {

			String inputFile = listOfFiles[i].toString();

			Hysteresis hys = new Hysteresis(inputFile, 18, 19);
			hys = new Hysteresis(inputFile, 18, 19);
			hys = new Hysteresis(inputFile, 28, 29);
			hys = new Hysteresis(inputFile, 38, 39);
			hys = new Hysteresis(inputFile, 48, 49);

		}
	}

}
