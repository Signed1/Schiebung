
public class Spielstand {
	private int[][] spielfeld;
	private int superzahl;
	
	public Spielstand(int[][] s, int t){
		this.spielfeld = s;
		this.superzahl = t;
	}
	
	public int[][] getSpielfeld() {
		return spielfeld;
	}
	public int getSuperzahl() {
		return superzahl;
	}
	public void setSpielfeld(int[][] spielfeld) {
		this.spielfeld = spielfeld;
	}
	public void setSuperzahl(int superzahl) {
		this.superzahl = superzahl;
	}
	public int getZelle(int pos_x, int pos_y){
		return spielfeld[pos_x][pos_y];
	}
	public int setZelle(int pos_x, int pos_y, int zahl){
		int alt = spielfeld[pos_x][pos_y];
		spielfeld[pos_x][pos_y] = zahl;
		return alt;
	}
}
