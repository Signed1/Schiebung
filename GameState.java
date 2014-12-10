package Schiebung;
public class GameState {
	private int[][] field; //An array of rows
	private int opNr;
	
	public GameState(int[][] s, int t){
		this.field = s;
		this.opNr = t;
	}
	
	public int[][] getField() {
		return field;
	}
	public int getOpNr() {
		return this.opNr;
	}
	public void setField(int[][] field) {
		this.field = field;
	}
	public void setOpNr(int OpNr) {
		this.opNr = OpNr;
	}
	public int getCell(int row, int column){
		return field[row][column];
	}
	public int setCell(int row, int column, int zahl){
		int alt = field[row][column];
		field[row][column] = zahl;
		return alt;
	}

	public int getHeight(){
		return this.field.length;
	}
	public int getWidth() {
		if (this.field[0] != null){
			return this.field[0].length;
		}
		return 0;
	}
	public String toString(){
		String rep = "";
		
		for(int i = 0; i < this.getHeight(); i++){
			for(int j = 0; j < this.getWidth(); j++){
				if(this.field[i][j] <= 9) rep = rep + " ";
				rep = rep + " " + this.field[i][j];
			}
			rep = rep + "\n";
		}
		rep = rep + "\n " + this.opNr;
		return rep;
	}
}
