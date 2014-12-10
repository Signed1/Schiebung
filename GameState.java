import java.lang.System;
import java.util.Arrays;

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
	public int rate(){
		//Build a single array, holding all numbers in order
		int[] singleArray = new int[this.getWidth() * this.getHeight()];
		int counter = 0;
		for(int i = 0; i<this.getHeight(); i++){
			for(int j = 0; j<this.getWidth(); j++){
				singleArray[counter] = this.getCell(i, j);
				counter++;
			}
		}

		//Rate it with bubble sort;
		boolean swapped = true;
		int rating = 0;

		while(swapped == true){
			swapped = false;
			for(int i = singleArray.length-1; i>0; i--){;
				if(singleArray[i] < singleArray[i-1]) {
					int tmp = singleArray[i];
					singleArray[i] = singleArray[i - 1];
					singleArray[i - 1] = tmp;
					rating++;
					swapped = true;
				}
			}
		}

		return rating;
	}
}
