package Schiebung;
public class Main {
	public static void main(String[] args){
		int[][] spielfeld = {{1,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,24,25}};
		int superzahl = 26;
		
		GameState test = new GameState(spielfeld, superzahl);	
		Spielfeld sp = new Spielfeld(test);
		
		System.out.println(sp.toString());	
		sp.shiftRow(3, true);	
		System.out.println(sp.toString());
		
		
		
	}
}
