package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import services.*;

@SuppressWarnings("serial")
public class activiteitServlet extends HttpServlet {
	gezinslidService service = ServiceProvider.glService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String actNaam = req.getParameter("actNaam");
		String oms = req.getParameter("omschrijving");
		int aID = 0;
		try {
			aID = Integer.parseInt(req.getParameter("aID"));
		} catch (NumberFormatException e) {
		}
		int bsn = 0;
		try {
			bsn = Integer.parseInt(req.getParameter("bsn"));
		} catch (NumberFormatException e) {
		}
		String pType = req.getParameter("optie");
		Object loggedInUserO = req.getSession().getAttribute("loggedGezinslid");
		String e_msgs = "";
		Gezinslid g = (Gezinslid) loggedInUserO;
		boolean wijzigingSuccess = false;

		if (pType.equals("verwijderAct")) {
			service.verwijderAct(actNaam);
			g = service.getAlleActiviteitenGezinslid(g);
			List<Activiteit> an = service.getAlleActiviteiten();
			req.getSession().setAttribute("activiteiten", an);
			req.getSession().setAttribute("loggedGezinslid", g);
			wijzigingSuccess = true;
			e_msgs = e_msgs + "Activiteit verwijderd<br>";

		} else if (pType.equals("activiteitWijzigen")) {
			if (actNaam.isEmpty() || actNaam == null) {
				e_msgs = e_msgs + "Controleer invoer!<br>";
			} else {
				if (oms.isEmpty() || oms == null) {
					oms = "(geen)";
				}
				Activiteit a = new Activiteit(actNaam, oms);
				service.wijzigActiviteit(a, g, actNaam);
				g = service.getAlleActiviteitenGezinslid(g);
				List<Activiteit> an = service.getAlleActiviteiten();
				req.getSession().setAttribute("activiteiten", an);
				req.getSession().setAttribute("loggedGezinslid", g);
				wijzigingSuccess = true;
				e_msgs = e_msgs + "Wijziging opgeslagen<br>";
			}
		} else if (pType.equals("voegActToe")) {
			if (actNaam.isEmpty() || actNaam == null) {
				e_msgs = e_msgs + "Naam ongeldig/niet ingevuld!<br>";
			} else {
				if (oms.isEmpty() || oms == null) {
					oms = "(geen)";
				}
				Activiteit a = new Activiteit(actNaam, oms);
				service.voegActToe(a, g);
				g = service.getAlleActiviteitenGezinslid(g);
				List<Activiteit> an = service.getAlleActiviteiten();
				req.getSession().setAttribute("activiteiten", an);
				req.getSession().setAttribute("loggedGezinslid", g);
				wijzigingSuccess = true;
				e_msgs = e_msgs + "Activiteit toegevoegd<br>";
			}
		} else if (pType.equals("koppelActToe")) {
			service.koppelActToe(aID, bsn);
			g = service.getAlleActiviteitenGezinslid(g);
			List<Activiteit> an = service.getAlleActiviteiten();
			req.getSession().setAttribute("activiteiten", an);
			req.getSession().setAttribute("loggedGezinslid", g);
			wijzigingSuccess = true;
			e_msgs = e_msgs + "Activiteit toegevoegd<br>";
		}

		RequestDispatcher rd = null;
		if (wijzigingSuccess) {
			req.setAttribute("msgs", e_msgs);
			rd = req.getRequestDispatcher("/ingelogd/overzicht.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("msgs", e_msgs);
			rd = req.getRequestDispatcher("/ingelogd/overzicht.jsp");
			rd.forward(req, resp);
		}
	}

}
