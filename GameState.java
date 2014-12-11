package Schiebung;

import java.lang.System;
import java.util.Arrays;

public class GameState  implements Comparable<GameState>{
	private int[][] field; //An array of rows
	private int opNr;
	private String hash;

	private int aStarH;
	private int aStarG;
	private int aStarF;

	private GameState predecessor;

	public GameState(int[][] s, int t, int aStarG, GameState predecessor){
		this.field = s;
		this.opNr = t;

		this.aStarH = this.rate(); //Precalculate values for A* to improve performance
		this.aStarG = aStarG+1; //Number of steps to get to this node
		this.aStarF = this.aStarG + this.aStarH; //Estimated complete cost using this node
		this.predecessor = predecessor;
	}

	public int getaStarH() {
		return aStarH;
	}
	public int getaStarG() {
		return aStarG;
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

	public int getaStarF() {
		return aStarF;
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

	public int[] toSingleArray(){
		//Build a single array, holding all numbers in order
		int[] singleArray = new int[this.getWidth() * this.getHeight()];
		int counter = 0;
		for(int i = 0; i<this.getHeight(); i++){
			for(int j = 0; j<this.getWidth(); j++){
				singleArray[counter] = this.getCell(i, j);
				counter++;
			}
		}
		return singleArray;
	}
	public int rate(){
		int[] singleArray = this.toSingleArray();

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

	public GameState shiftRow(int rowNr, boolean direction){
		//direction false = left, direction true = right
		int opNr;

		GameState createdState = new GameState(this.field, this.opNr, this.aStarG, this);

		if(direction == false){
			opNr = createdState.getCell(rowNr, 0); //Save the future opNr
			for (int i = 0; i < createdState.getWidth()-1; i++) {
				createdState.setCell(rowNr, i, createdState.getCell(rowNr, i + 1)); //Shift all cells one cell to the left
			}
			createdState.setCell(rowNr, createdState.getWidth() - 1, createdState.getOpNr()); //Overwrite the leftmost cell with the old opNr
		}
		else {
			opNr = createdState.getCell(rowNr, createdState.getWidth() - 1); //Save the future opNr
			for (int i = createdState.getWidth()-1; i > 0; i--) {
				createdState.setCell(rowNr, i, createdState.getCell(rowNr, i - 1)); //Shift all cells one cell to the right
			}
			createdState.setCell(rowNr, 0, createdState.getOpNr()); //Overwrite rightmost cell with old opNr
		}

		createdState.setOpNr(opNr); //Set new OpNr

		return createdState;
	}

	public GameState shiftColumn(int columnNr, boolean direction){
		//direction false = upwards, true = downwards
		int opNr;

		GameState createdState = new GameState(this.field, this.opNr, this.aStarG, this);

		if(direction == false){
			opNr = createdState.getCell(0, columnNr); //Save the topmost cell as future opNr
			for(int i = 0; i<createdState.getHeight()-1; i++){
				createdState.setCell(i, columnNr, createdState.getCell(i + 1, columnNr)); //Shift all cells one upwards
			}
			createdState.setCell(createdState.getHeight()-1, columnNr, createdState.getOpNr());
		}

		else{
			opNr = createdState.getCell(createdState.getHeight()-1, columnNr); //Save the bottommost cell as future opNr
			for(int i = createdState.getHeight()-1; i>0; i--){
				createdState.setCell(i, columnNr, createdState.getCell(i-1, columnNr)); //Shift all cells one downwards
			}
			createdState.setCell(0, columnNr, createdState.getOpNr());
		}

		createdState.setOpNr(opNr); //Set new OpNr
		return createdState;
	}

	public String hash(){
		if(this.hash.equals("")){
			this.hash = Arrays.toString(this.toSingleArray());
		}
		return this.hash;
	}

	public int compareTo(GameState o){
		if(this.hash().equals(o.hash())) return 0;
		return this.aStarF - o.aStarF;
	}
}
