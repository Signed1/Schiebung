package Schiebung;

import java.io.IOException;
import java.lang.Integer;
import java.lang.System;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.FileReader;

public class Main {
	public static void main(String[] args) throws IOException{

		int superzahl = 10;
		CSVReader reader = null;

		if(args.length != 1){
			System.out.println("First arg has to be a filename to read");
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

		GameState test = new GameState(spielfeld, superzahl, 0, null);

		System.out.println(test);

		ArrayList<GameState> open = new ArrayList<GameState>();
		HashMap<String, GameState> closed = new HashMap<String, GameState>();


		open.add(test);
		while(open.size() > 0) {
			int lowest = 0;
			for (int i = 1; i < open.size(); i++) {
				if (open.get(i).rate() < open.get(lowest).rate()) {
					lowest = i;
				}
			}
			expand(open.get(lowest), open, closed);
			closed.put(open.get(lowest).hash(), open.get(lowest));
			open.remove(lowest);
		}
		

	}
	public static boolean valid(GameState nodeToCheck){
		int[] serial = nodeToCheck.toSingleArray();
		for(int i = 0; i < serial.length-1; i++){
			if(serial[i]>serial[i+1]) return false;
		}
		return true;
	}

	public static ArrayList<GameState> checkNode(GameState nodeToCheck, ArrayList<GameState> open, HashMap<String, GameState> closed) {
		if(valid(nodeToCheck)){
			System.out.println("Found shortest way");
			System.out.println("Die Weglänge ist: "+(nodeToCheck.getaStarG()-1));
			System.out.println(nodeToCheck.toString());
			System.exit(0);
		}

		if(!closed.containsKey(nodeToCheck.hash())) {
			if (open.contains(nodeToCheck)) {
				if (nodeToCheck.getaStarF() < open.get(open.indexOf(nodeToCheck)).getaStarF()) {
					open.set(open.indexOf(nodeToCheck), nodeToCheck);
				}
			}
			else {
				open.add(nodeToCheck);
			}
		}
		return open;
	}

	public static int expand(GameState s, ArrayList<GameState> open, HashMap<String, GameState> closed){

		//System.out.println("-----------------------------");
		//System.out.println("Open list size: " + open.size());
		//System.out.println("Closed list size: " + closed.size());
		//System.out.println("Current depth is: " + s.getaStarG());
		///System.out.println("My estimated reminder: " + s.getaStarH());

		//System.out.println(s.toString());

		for(int i = 0; i<s.getWidth(); i++){
			GameState newGameState = s.shiftColumn(i, true);
			open = checkNode(newGameState, open, closed);

			GameState newGameState2 = s.shiftColumn(i, false);
			open = checkNode(newGameState2, open, closed);
		}

		for(int j = 0; j<s.getHeight(); j++){
			GameState newGameState = s.shiftRow(j, true);
			open = checkNode(newGameState, open, closed);

			
			GameState newGameState2 = s.shiftRow(j, false);
			open = checkNode(newGameState2, open, closed);
		}
		return 1;
	}
}
