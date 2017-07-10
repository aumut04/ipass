package model;

public class Activiteit {
	private int activiteitID;
	private String activiteitNaam;
	private String omschrijving;
	private String status;

	public Activiteit() {
	}

	public Activiteit(String actNaam, String oms) {
		super();
		this.activiteitNaam = actNaam;
		this.omschrijving = oms;
		this.status = status;
	}
	
	public Activiteit(int aID, String actNaam, String oms) {
		super();
		this.activiteitID = aID;
		this.activiteitNaam = actNaam;
		this.omschrijving = oms;
		this.status = status;
	}

	public Activiteit(int aID, String actNaam, String oms, Gezinslid gl) {
		super();
		this.activiteitID = aID;
		this.activiteitNaam = actNaam;
		this.omschrijving = oms;
		this.status = status;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}