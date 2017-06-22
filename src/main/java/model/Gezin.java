package model;

import java.util.ArrayList;

public class Gezin {
	private int gezinID;
	private String gezinsNaam;
	private ArrayList<Gezinslid> gezinsLeden;

	
	public Gezin() {
	}
	
	public Gezin(int gezinID_i, String gezinsNaam_i) {
		super();
		this.gezinID = gezinID_i;
		this.gezinsNaam = gezinsNaam_i;
		this.gezinsLeden = new ArrayList<Gezinslid>();
	}

	public int getGezinID() {
		return gezinID;
	}

	public String getGezinsNaam() {
		return gezinsNaam;
	}

	public void setGezinsNaam(String gezinsNaam) {
		this.gezinsNaam = gezinsNaam;
	}

	public Gezinslid getGezinslid(int bsn) {
		return getGezinslid(bsn);
	}

	public ArrayList<Gezinslid> getGezinsLeden() {
		return gezinsLeden;
	}

	public void setGezinsLeden(ArrayList<Gezinslid> gezinsLeden) {
		this.gezinsLeden = gezinsLeden;
	}
}
