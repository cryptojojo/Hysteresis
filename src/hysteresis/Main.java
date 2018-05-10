package hysteresis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		// just load fxml file and display it in the stage:

		primaryStage.getIcons().add(new Image(ClassLoader.getSystemResourceAsStream("hysteresis/hysteresis.png")));
		primaryStage.setTitle("Hysteresis");
		FXMLLoader appLoad = new FXMLLoader(getClass().getResource("application.fxml"));
		Parent root = appLoad.load();
		Scene scene = new Scene(root);
		primaryStage.setScene(scene);

		primaryStage.show();

	}

	public static void main(String[] args) throws Exception {

		launch(args);

	}
}
