
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
				gameState.setCell(rowNr, i, gameState.getCell(i + 1, rowNr)); //Shift all cells one cell to the right
			}
			gameState.setCell(rowNr, 0, gameState.getOpNr()); //Overwrite the leftmost cell with the old opNr
		}
		else {
			opNr = gameState.getCell(rowNr, 0); //Save the future opNr
			for (int i = 1; i < gameState.getWidth(); i++) {
				gameState.setCell(rowNr, i, gameState.getCell(i - 1, rowNr)); //Shift all cells one cell to the left
			}
			gameState.setCell(rowNr, gameState.getWidth() - 1, gameState.getOpNr()); //Overwrite rightmost cell with old opNr
		}

		gameState.setOpNr(opNr); //Set new OpNr
		return opNr;
	}

	public int shiftColumn(int columnNr, boolean direction){
		//direction false = upwards, true = downwards
		int opNr;

		if(direction == false){
			opNr = gameState.getCell(0, columnNr); //Save the topmost cell as future opNr
			for(int i = 0; i<gameState.getHeight()-1; i++){
				gameState.setCell(i, columnNr, gameState.getCell(i+1, columnNr)); //Shift all cells one upwards
			}
			gameState.setCell(gameState.getHeight()-1, columnNr, gameState.getOpNr());
		}

		else{
			opNr = gameState.getCell(gameState.getHeight()-1, columnNr); //Save the bottommost cell as future opNr
			for(int i = 1; i<gameState.getHeight(); i++){
				gameState.setCell(i, columnNr, gameState.getCell(i - 1, columnNr)); //Shift all cells one upwards
			}
			gameState.setCell(0, columnNr, gameState.getOpNr());
		}

		gameState.setOpNr(opNr); //Set new OpNr
		return opNr;
	}
}
