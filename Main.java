package Schiebung;

import java.lang.Comparable;
import java.lang.String;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Main{
	public static void main(String[] args){
		int[][] spielfeld = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,29,25}};
		int superzahl = 26;
		
		GameState test = new GameState(spielfeld, superzahl, 0, null);
		
		System.out.println(test.toString());

		System.out.println(test.shiftRow(3, true));

		System.out.println(test.rate());

		ArrayList<GameState> open;

	}

	public ArrayList<GameState> checkNode(GameState nodeToCheck, ArrayList<GameState> open, HashMap<String, GameState> closed) {
		if(!closed.containsKey(nodeToCheck.hash())) {
			if (open.contains(nodeToCheck)) {
				if (nodeToCheck.getaStarF < open.get(open.indexOf(nodeToCheck)).getaStarF()) {
					open.set(open.indexOf(nodeToCheck), nodeToCheck);
				}
			}
			else {
				open.add(nodeToCheck);
			}
		}
		return open;
	}

	public int expand(GameState s, ArrayList<GameState> open, HashMap<String, GameState> closed){
		for(int i = 0; i<s.getWidth(); i++){
			GameState newGameState = t.shiftColumn(i, true);
			open = checkNode(newGameState, open, closed);

			GameState newGameState = t.shiftColumn(i, false);
			open = checkNode(newGameState, open, closed);
		}

		for(int j = 0; j<s.getHeight(); j++)
			GameState newGameState = t.shiftRow(i, true);
			open = checkNode(newGameState, open, closed);

			GameState newGameState = t.shiftRow(i, false);
			open = checkNode(newGameState, open, closed);
		}
	}
}
