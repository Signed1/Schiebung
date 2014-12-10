package Schiebung;
public class Spielfeld {
	private GameState gameState;
	
	public Spielfeld(GameState sp){
		gameState = sp;
	}

	public String toString(){
		return this.gameState.toString();
	}
}
