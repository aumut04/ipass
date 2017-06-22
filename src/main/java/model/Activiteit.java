package model;

public class Activiteit {
	private int activiteitID;
	private String activiteitNaam;
	private String omschrijving;

	public Activiteit() {
	}

	public Activiteit(String actNaam, String oms) {
		super();
		this.activiteitNaam = actNaam;
		this.omschrijving = oms;
	}
	
	public Activiteit(int aID, String actNaam, String oms) {
		super();
		this.activiteitID = aID;
		this.activiteitNaam = actNaam;
		this.omschrijving = oms;
	}

	public Activiteit(int aID, String actNaam, String oms, Gezinslid gl) {
		super();
		this.activiteitID = aID;
		this.activiteitNaam = actNaam;
		this.omschrijving = oms;
	}

	public int getActiviteitID() {
		return activiteitID;
	}

	public String getActiviteitNaam() {
		return activiteitNaam;
	}

	public void setActNaam(String actNaam) {
		this.activiteitNaam = actNaam;
	}

	public String getOmschrijving() {
		return omschrijving;
	}

	public void setOmschrijving(String oms) {
		this.omschrijving = oms;
	}
}