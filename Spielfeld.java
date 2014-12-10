
public class Spielfeld {
	private GameState gameState;
	public Spielfeld(GameState sp){
		gameState = sp;
	}

	//TODO: Use arraycopy as a faster way to shift the array if performance gets a problem
	public int shiftRow(int rowNr, boolean direction){
		//direction false = left, direction true = right
		int opNr;

		if(direction == false) {
			opNr = gameState.getCell(rowNr, gameState.getWidth() - 1); //Save the future opNr
			for (int i = 0; i < gameState.getWidth()-1; i++) {
				gameState.setCell(rowNr, i, gameState.getCell(i+1, rowNr)); //Shift all cells one cell to the right
			}
			gameState.setCell(rowNr, 0, gameState.getOpNr()); //Overwrite the leftmost cell with the old opNr
		}
		else{
			opNr = gameState.getCell(rowNr, 0); //Save the future opNr
			for (int i = 1; i < gameState.getWidth(); i++) {
				gameState.setCell(rowNr, i, gameState.getCell(i-1, rowNr)); //Shift all cells one cell to the left
			}
			gameState.setCell(rowNr, gameState.getWidth()-1, gameState.getOpNr()); //Overwrite rightmost cell with old opNr
		}

		return opNr;
	}

	public int shiftColumn(int columnNr, boolean direction){
		//direction false = upwards, true = downwards
		int opNr;

		if(direction == false){
			opNr = gameState.getCell(0, columnNr); //Save the topmost cell as future opNr
			for(int i = 0; i<gameState.getHeight(); i++){
				gameState.setCell(i, columnNr, gameState.getCell(i+1, columnNr)); //Shift all cells one upwards
			}
			gameState.setCell()
		}
		else{


		}

	}
	
	public int schieben(int pos_x, int pos_y){
		//Check for vertical edges except corners
		if(pos_y > 0 && pos_y < 4){
			if(pos_x == 0){
				int superzahl ;
			}
			if(pos_x == 4){
			}
			
		} 
		else if((pos_y == 0 || pos_y == 4) && (pos_x > 0 && pos_x < 4)){ //Check for horizontal edges except corners
		}
		
		
	}
	public int schieben(int pos_x, int pos_y, boolean horizontal){
		
		
	}
	
}
