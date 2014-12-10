package Schiebung;
public class Main {
	public static void main(String[] args){
		int[][] spielfeld = {{100,2,3,4,5},{6,7,8,9,10},{11,12,13,14,15},{16,17,18,19,20},{21,22,23,26,25}};
		int superzahl = 26;
		
		GameState test = new GameState(spielfeld, superzahl);	

		Spielfeld sp = new Spielfeld(test);
		
		System.out.println(test.toString());

		System.out.println(test.rate());
		
	}
}
