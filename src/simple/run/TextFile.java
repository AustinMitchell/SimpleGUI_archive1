package simple.run;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TextFile {
	public static ArrayList<String> load(String pathName) {
		ArrayList<String> lines = new ArrayList<String>();
		
		try {
			BufferedReader textReader = new BufferedReader(new FileReader(pathName));
			
			while(true) {
				String nextLine = textReader.readLine();
				if (nextLine == null) {
					break;
				} else {
					lines.add(nextLine);
				}
			}
			
			textReader.close();
		} catch (IOException e) {
			System.out.println("Error loading file. Returning empty list.");
		} catch (Exception e) {
		}
		
		return lines;
	}
	public static <T> void save(T[] lines, String pathName) {
		ArrayList<T> linesList = new ArrayList<T>();
		for (T line : lines) 
			linesList.add(line);
		save(linesList, pathName);
	}
	public static <T> void save(ArrayList<T> lines, String pathName) {		
		try {
			BufferedWriter textWriter = new BufferedWriter(new FileWriter(pathName));
			
			for (T s : lines) {
				textWriter.write(s.toString());
				textWriter.newLine();
			}
			
			textWriter.close();
		} catch (IOException e) {
			System.out.println("Error Writing file. Possibly unexpected output.");
		} catch (Exception e) {
		}
	}
	
	public static <T> ArrayList<String> arrayMapToList(T[][] map) {
		ArrayList<String> newList = new ArrayList<String>();
		for (int i=0; i<map.length; i++) {
			String newString = "";
			for (int j=0; j<map[i].length; j++) {
				if (j != map[i].length) {
					newString += map[i][j].toString().trim() + " ";
				} else {
					newString += map[i][j].toString().trim();
				}
			}	
			newList.add(newString);
		}
		return newList;
	}
	public static String[][] listToArrayMap(ArrayList<String> list) {
		String[][] newMap = new String[list.size()][list.get(0).split(" ").length];
		for (int i=0; i<newMap.length; i++) {
			int j = 0;
			for (String s : list.get(i).split(" ")) {
				newMap[i][j] = s;
				j++;
			}
		}
		return newMap;
	}
}
