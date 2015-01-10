package Schiebung;

import java.util.ArrayList;
import java.util.HashMap;

public class Astar {
	ArrayList<GameState> open;
	HashMap<String, GameState> closed;
	
	public Astar(GameState gs){
		open = new ArrayList<GameState>();
		closed = new HashMap<String, GameState>();
		open.add(gs);
	}
	
	public void run(){
		while(open.size() > 0) {
			int lowest = 0;
			for (int i = 1; i < open.size(); i++) {
				if (open.get(i).getaStarH() < open.get(lowest).getaStarH()) {
					lowest = i;
				}
			}
			expand(open.get(lowest));
			closed.put(open.get(lowest).hash(), open.get(lowest));
			open.remove(lowest);
		}
	}
	
	public boolean valid(GameState nodeToCheck){
		int[] serial = nodeToCheck.toSingleArray();
		for(int i = 0; i < serial.length-1; i++){
			if(serial[i]>serial[i+1]) return false;
		}
		return true;
	}
	
	public void checkNode(GameState nodeToCheck) {
		if(valid(nodeToCheck)){
			System.out.println("Found shortest way");
			System.out.println("Die Weglaenge ist: "+(nodeToCheck.getaStarG()-1));
			System.out.println("Der weg ist: ");
			while(nodeToCheck.getPredecessor() != null){
				System.out.println(nodeToCheck.toString()+"\n\n");
				nodeToCheck = nodeToCheck.getPredecessor();
			}
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
	}
	
	public void expand(GameState s){
		/*
		System.out.println("-----------------------------");
		System.out.println("Open list size: " + open.size());
		System.out.println("Closed list size: " + closed.size());
		System.out.println("Current depth is: " + s.getaStarG());
		System.out.println("My estimated reminder: " + s.getaStarH());
		System.out.println(s.toString());
		*/

		for(int i = 0; i<s.getWidth(); i++){
			GameState newGameState = s.shiftColumn(i, true);
			checkNode(newGameState);

			newGameState = s.shiftColumn(i, false);
			checkNode(newGameState);
		}

		for(int j = 0; j<s.getHeight(); j++){
			GameState newGameState = s.shiftRow(j, true);
			checkNode(newGameState);

			newGameState = s.shiftRow(j, false);
			checkNode(newGameState);
		}
	}
}
