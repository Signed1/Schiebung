
public class Spielfeld {
	public Spielstand aktuell;
	
	
	public Spielfeld(Spielstand sp){
		aktuell = sp;
	}
	
	public int schieben(int pos_x, int pos_y){
		//Check for vertical edges except corners
		if((pos_x == 0 || pos_x == 4) && (pos_y > 0 && pos_y < 4)){
			if(pos_x == 0){
				int superzahl ;
			}
			
		} 
		else if((pos_y == 0 || pos_y == 4) && (pos_x > 0 && pos_x < 4)){//Check for horizontal edges except corners	
		}
		
		
	}
	public int schieben(int pos_x, int pos_y, boolean horizontal){
		
		
	}
	
}
