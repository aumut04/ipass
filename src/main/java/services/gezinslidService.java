package services;

import java.util.ArrayList;
import java.util.List;

import dao.*;
import model.*;

public class gezinslidService {
	private gezinDAO gezinDAO = new gezinDAO();
	private gezinslidDAO glDAO = new gezinslidDAO();
	private activiteitDAO actDAO = new activiteitDAO();
	private testSQL test = new testSQL();

	public ArrayList<Gezinslid> getAlleGezinsleden() {
		ArrayList<Gezinslid> glList = glDAO.getAlleGezinsleden();
		Gezin g = new Gezin();
		g.setGezinsLeden(glList);		
		for (Gezinslid gl : glList) {
			gl = actDAO.getAlleActiviteitenGezinslid(gl);
		}
		return glList;
	}

	public Gezin getGezin(int gezinID) {
		return gezinDAO.getGezin(gezinID);
	}

	public void voegGezinslidToe(Gezinslid gl, Gezin g) {
		glDAO.voegGezinslid(gl, g);
	}

	public Gezinslid getGezinslid(int bsn) {
		return glDAO.getGezinslid(bsn);
	}

	public void wijzigGezinslid(Gezinslid gl) {
		glDAO.wijzigGezinslid(gl);
	}

	public void verwijderGezinslid(Gezinslid gl) {
		glDAO.verwijderGezinslid(gl);
	}

	public void verwijderGezinslid(int bsn) {
		glDAO.verwijderGezinslid(bsn);
	}

	public void voegActToe(Activiteit a, Gezinslid gl) {
		actDAO.voegActToe(a);
	}

	public List<Activiteit> getAlleActiviteitenGezinslid(int BSN) {
		return actDAO.getAlleActiviteitenGezinslid(BSN);
	}
	
	public Gezinslid getAlleActiviteitenGezinslid(Gezinslid g) {
		return actDAO.getAlleActiviteitenGezinslid(g);
	}

	public void verwijderAct(String aNaam) {
		actDAO.verwijderAct(aNaam);
	}

	public void wijzigActiviteit(Activiteit a, Gezinslid gl, String activiteitnaam) {
		actDAO.wijzigActiviteit(a, gl, activiteitnaam);
		
	}
	
	public ArrayList<Gezinslid> getAlleGezinsledenGezin(int gID){
		return glDAO.getAlleGezinsledenGezin(gID);
	}

}
