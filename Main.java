package Schiebung;
public class Main {
	public static void main(String[] args){
		int[][] spielfeld = {{100,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,26,25}};
		int superzahl = 26;
		
		GameState test = new GameState(spielfeld, superzahl);	

		Spielfeld sp = new Spielfeld(test);
		
		System.out.println(test.toString());

		System.out.println(test.rate());

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
			closed.set(open.get(lowest).hash(), open.get(lowest));
			open.remove(lowest);
		}

	}

	public static ArrayList<GameState> checkNode(GameState nodeToCheck, ArrayList<GameState> open, HashMap<String, GameState> closed) {
		if(nodeToCheck.rate == 0){
			System.out.println("Found shortest way");
			System.out.println(nodeToCheck.toString());
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
		for(int i = 0; i<s.getWidth(); i++){
			GameState newGameState = s.shiftColumn(i, true);
			open = checkNode(newGameState, open, closed);

			GameState newGameState = s.shiftColumn(i, false);
			open = checkNode(newGameState, open, closed);
		}

		for(int j = 0; j<s.getHeight(); j++){
			GameState newGameState = s.shiftRow(i, true);
			open = checkNode(newGameState, open, closed);

			GameState newGameState = s.shiftRow(i, false);
			open = checkNode(newGameState, open, closed);
		}
	}
}
