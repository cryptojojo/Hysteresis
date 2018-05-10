package hysteresis;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * for finding the segment areas and getting the area under curve - also handles
 * printing
 * 
 * @author Joe Millrt
 *
 */
public class Hysteresis {

	private String inputFile;
	private int num1 = 0;
	private int num2 = 0;
	private ArrayList<ArrayList<String>> result;

	/**
	 * Constructor for hysteresis class
	 * 
	 * @param inputFile
	 * @param num1
	 * @param num2
	 * @throws IOException
	 */
	Hysteresis(String inputFile, int num1, int num2) throws IOException {
		this.inputFile = inputFile;
		this.num1 = num1;
		this.num2 = num2;

		execute();

	}

	/**
	 * Drives the hysteresis class to crop, print, and get area under curve
	 * 
	 * @throws IOException
	 */
	private void execute() throws IOException {

		//////////////////////////////////////////////////////////////////////////////////////////////////////
		// Gets the info from the file and puts in in an arraylist of arraylists
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		StringTokenizer st;
		BufferedReader TSVFile = new BufferedReader(new FileReader(inputFile));
		String dataRow = TSVFile.readLine(); // Read first line.

		ArrayList<ArrayList<String>> data = new ArrayList<ArrayList<String>>();

		while (dataRow != null) {
			st = new StringTokenizer(dataRow, "\t");
			ArrayList<String> dataArray = new ArrayList<String>();
			while (st.hasMoreElements()) {
				dataArray.add(st.nextElement().toString());
			}

			data.add(dataArray);

			dataRow = TSVFile.readLine(); // Read next line of data.
		}
		TSVFile.close();

		for (int i = 1; i < data.size(); i++) {
			data.get(i).remove(3);
			data.get(i).remove(3);
			data.get(i).remove(3);
		}
		//////////////////////////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////////////////////////////////////////////////////////////////////////////

		// removes time and position from title
		data.get(0).remove(3);
		data.get(0).remove(3);

		// creates the null element to replace unnecessary info in arraylist
		ArrayList<String> nullElement = new ArrayList<String>();
		nullElement.add("null");

		// replaces unnecessary info with the null element
		for (int i = 1; i < data.size(); i += 1) {
			if (!(Double.parseDouble(data.get(i).get(2)) == num1) && !(Double.parseDouble(data.get(i).get(2)) == num2))
				data.set(i, nullElement);
		}

		// removes all null elements
		data.removeIf(nullElement::equals);

		// zeros the data
		data = zeroingData(data);

		// adds the area under the curve to the header
		data = addAreaUnderCurve(data);

		// saves the data
		result = data;

		// prints the table
		printTable(data);

	}

	/**
	 * prints the table
	 * 
	 * @param data
	 */
	private void printTable(ArrayList<ArrayList<String>> data) {

		String ans = "";

		// puts the arraylist in string form so it can be manipulated
		for (int i = 0; i < data.size(); i++)
			ans += (data.get(i) + "\r\n");

		// gets rid of brackets - proper csv format
		ans = ans.replace("[", "");
		ans = ans.replace("]", "");

		// prints the table
		PrintWriter output;
		try {
			output = new PrintWriter(inputFile + "_" + num1 + "to" + num2 + ".csv");
			output.println(ans);
			output.close();
		} catch (FileNotFoundException e) {
			System.out.println("Problem creating/overwriting table output file");
			e.printStackTrace();
		}

	}

	/**
	 * gets the area under the curve and puts it in the header
	 * 
	 * @param data
	 * @return
	 */
	private ArrayList<ArrayList<String>> addAreaUnderCurve(ArrayList<ArrayList<String>> data) {

		Double areaUnderCurve = 0.0;

		for (int i = 1; i < data.size() - 1; i++) {

			Double leftEq = Double.parseDouble(data.get(i).get(1))
					+ ((Double.parseDouble(data.get(i + 1).get(1)) - Double.parseDouble(data.get(i).get(1))) / 2);
			Double rightEq = Double.parseDouble(data.get(i + 1).get(0)) - Double.parseDouble(data.get(i).get(0));

			areaUnderCurve += (leftEq * rightEq);

		}

		BigDecimal bd = new BigDecimal(areaUnderCurve).setScale(10, RoundingMode.CEILING);

		data.get(0).add("{AREA UNDER CURVE: " + bd + "}");
		return data;
	}

	/**
	 * subtracts the first strain value for the entire strain column, subtracts the
	 * first stress value for the entire stress column, divides strain column by (1+
	 * offsetstrain), multiply stress column by (1+ offsetstrain)
	 * 
	 * @param data
	 * @return zeroed data
	 */
	private ArrayList<ArrayList<String>> zeroingData(ArrayList<ArrayList<String>> data) {

		double firstStrainVal = Double.parseDouble(data.get(1).get(0));
		double firstStressVal = Double.parseDouble(data.get(1).get(1));

		// subtract the first strain value for the entire strain column
		for (int i = 1; i < data.size(); i++) {
			String newStrain = Double.toString(Double.parseDouble(data.get(i).get(0)) - firstStrainVal);
			BigDecimal bd = new BigDecimal(newStrain).setScale(6, RoundingMode.CEILING);
			data.get(i).set(0, bd.toString());
		}

		// subtract the first stress value for the entire stress column
		for (int i = 1; i < data.size(); i++) {
			String newStress = Double.toString(Double.parseDouble(data.get(i).get(1)) - firstStressVal);
			BigDecimal bd = new BigDecimal(newStress).setScale(6, RoundingMode.CEILING);
			data.get(i).set(1, bd.toString());
		}

		// divide the entire strain c0lumn by (1 + offeststrain)
		for (int i = 1; i < data.size(); i++) {
			String newStrain = Double.toString(Double.parseDouble(data.get(i).get(0)) / (1 + firstStrainVal));
			BigDecimal bd = new BigDecimal(newStrain).setScale(6, RoundingMode.CEILING);
			data.get(i).set(0, bd.toString());
		}

		// subtract the first stress value for the entire stress column
		for (int i = 1; i < data.size(); i++) {
			String newStress = Double.toString(Double.parseDouble(data.get(i).get(1)) * (1 + firstStrainVal));
			BigDecimal bd = new BigDecimal(newStress).setScale(6, RoundingMode.CEILING);
			data.get(i).set(1, bd.toString());
		}

		return data;
	}

}
