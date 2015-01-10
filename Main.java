package Schiebung;

import java.io.IOException;
import java.lang.Integer;
import java.lang.System;
import java.util.ArrayList;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws IOException{

		int superzahl = 10;
		CSVReader reader = null;

		if(args.length != 1){
			System.out.println("First arg has to be a filename to read");
			System.exit(1);
		}

		try {
			reader = new CSVReader(new FileReader(args[0]), ';');
		}
		catch (IOException e){
			System.out.println(e.toString());
		}

		String [] nextLine;
		ArrayList<int[]> parsedList = new ArrayList<int[]>();

		while ((nextLine = reader.readNext()) != null) {
			if(!nextLine[1].equals("Freies Teil")) {
				int[] intLine = new int[nextLine.length];
				for(int i = 0; i<nextLine.length; i++) {
					intLine[i] = Integer.parseInt(nextLine[i]);
				}

				parsedList.add(intLine);
			}
			else{
				superzahl = Integer.parseInt(nextLine[0]);
			}
		}

		int spielfeld[][] = new int[parsedList.size()][parsedList.get(0).length];
		for(int i = 0; i<parsedList.size(); i++){
			spielfeld[i] = parsedList.get(i);
		}

		GameState input = new GameState(spielfeld, superzahl, 0, null);
		Astar game = new Astar(input);
		game.run();
		System.out.println(input);
	}
}
