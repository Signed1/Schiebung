package Schiebung;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
	public static void main(String[] args){
		//int[][] spielfeld = {{1,7,8,9,10},{2,6,4,11,13},{15,5,3,12,14},{16,18,19,20,21},{17,22,23,24,25}};
		int[][] spielfeld = {{9,8,7},{6,5,4},{3,2,1}};
		//int[][] 
		int superzahl = 10;
		
		GameState test = new GameState(spielfeld, superzahl, 0, null);	

		Spielfeld sp = new Spielfeld(test);
		
		

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

	public static ArrayList<GameState> checkNode(GameState nodeToCheck, ArrayList<GameState> open, HashMap<String, GameState> closed) {
		if(nodeToCheck.getaStarH() == 0){
			System.out.println("Found shortest way");
			System.out.println(nodeToCheck.getaStarG());
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
		System.out.println("Open list size: " + open.size());
		System.out.println("Closed list size: " + closed.size());
		System.out.println(s.toString());

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
