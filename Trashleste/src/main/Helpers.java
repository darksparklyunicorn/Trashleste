package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Helpers {
	
	public static String loadFileToString(String path) {
		StringBuilder builder = new StringBuilder();
		
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(Helpers.class.getResourceAsStream(path)));
			String line;
			while((line=br.readLine()) != null) {
				builder.append(line+"\n");
			}
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		return builder.toString();
	}
	
	public static int parseInt(String num) {
		try {
			return Integer.parseInt(num);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return 0;
		}
	}
	
	public static String loadFileToStringScanner(String path) {
		StringBuilder builder = new StringBuilder();
		
		Scanner scanner;
		try {
			scanner = new Scanner(new File(path));
		String line = "";
		while(scanner.hasNext()) {
			builder.append(line+"\n");
		}
		scanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return builder.toString();
	}

}
