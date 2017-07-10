package model;

import java.util.ArrayList;

public class Gezinslid {
	private int gezinsLidID;
	private int BSN;
	private String gezinslidNaam;
	private String wachtwoord;
	private ArrayList<Activiteit> activiteiten;

	
	public Gezinslid(int gId, int bsn, String lidNaam, String wachtwoord) {
		super();
		this.gezinsLidID = gId;
		this.BSN = bsn;
		this.gezinslidNaam = lidNaam;
		this.wachtwoord = wachtwoord;
		this.activiteiten = new ArrayList<Activiteit>();
	}
	
	public Gezinslid(int bsn, String lidNaam, String wachtwoord) {
		super();
		this.BSN = bsn;
		this.gezinslidNaam = lidNaam;
		this.wachtwoord = wachtwoord;
		this.activiteiten = new ArrayList<Activiteit>();
	}

	public int getBSN() {
		return BSN;
	}
	
	public int getGezinsLidID(){
		return gezinsLidID;
	}
	
	public void setGezinsLidID(int gID){
		gezinsLidID = gID;
	}
	
	public String getWachtwoord() {
		return wachtwoord;
	}
	
	public String getGezinslidNaam() {
		return gezinslidNaam;
	}

	public void setGezinslidNaam(String lidNaam) {
		this.gezinslidNaam = lidNaam;
	}

	public Activiteit getActiviteit(int activiteitID) {
		Activiteit a = new Activiteit();
		for (Activiteit act : activiteiten) {
			if (act.getActiviteitID() == activiteitID) {
				a = act;
			}
		}
		return a;
	}

	public ArrayList<Activiteit> getActiviteiten() {
		return activiteiten;
	}

	public void voegActToe(Activiteit a) {
		activiteiten.add(a);
	}

	public void verwijderAct(Activiteit a) {
		activiteiten.remove(a);
	}

	public void setActiviteiten(ArrayList<Activiteit> activiteiten) {
		this.activiteiten = activiteiten;
	}
	
	public boolean checkWw(String ww) {
		boolean test = false;
		if (this.getWachtwoord().equals(ww)) {
			test = true;
		}
		return test;
	}
}
