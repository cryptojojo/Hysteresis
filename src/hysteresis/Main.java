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

		String inputFile = "C:/Users/Joe/Desktop/GTR_stuff/G892_60C_EB_SCL_2";

		Hysteresis hys8to9 = new Hysteresis(inputFile, 8, 9);
		Hysteresis hys18to19 = new Hysteresis(inputFile, 18, 19);
		
		
	}
}