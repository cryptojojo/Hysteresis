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

public class Main {

	public static void main(String[] args) throws Exception {

		String inputFile = "C:/Users/Joe/Desktop/GTR_stuff/G892_60C_EB_SCL_1";

		Hysteresis hys = new Hysteresis(inputFile, 18, 19);
		// hys = new Hysteresis(inputFile, 18, 19);
		// hys = new Hysteresis(inputFile, 28, 29);
		// hys = new Hysteresis(inputFile, 38, 39);
		// hys = new Hysteresis(inputFile, 48, 49);

	}
}
