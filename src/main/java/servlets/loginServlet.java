package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import services.*;

@SuppressWarnings("serial")
public class loginServlet extends HttpServlet {
	gezinslidService service = ServiceProvider.glService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String BSN = req.getParameter("bsn");
		String wachtwoord = req.getParameter("wwoord");
		String e_msgs = "";
		Gezinslid g = null;
		boolean logSuccess = false;

		if (BSN.isEmpty() || BSN == null) {
			e_msgs = e_msgs + "Gebruikersnaam ongeldig/niet ingevuld!<br>";
		} else if (wachtwoord.isEmpty() || wachtwoord == null) {
			e_msgs = e_msgs + "Wachtwoord ongeldig/niet ingevuld!<br>";
		} else {
			g = service.getGezinslid(Integer.parseInt(BSN));
			if (g != null) {
				logSuccess = g.checkWw(wachtwoord);
			} else {
				e_msgs = e_msgs + "Er is iets misgegaan tijdens het inloggen!<br>";
			}
		}

		if (logSuccess) {
			g = service.getAlleActiviteitenGezinslid(g);
			Gezin gn = service.getGezin(g.getGezinsLidID());
			gn.setGezinsLeden(service.getAlleGezinsledenGezin(gn.getGezinID()));
			List<Activiteit> an = service.getAlleActiviteiten();
			req.getSession().setAttribute("activiteiten", an);
			Cookie c = new Cookie("gebruikersNaam", BSN);
			c.setMaxAge(-1);
			resp.addCookie(c);			
			req.getSession().setAttribute("loggedGezinslid", g);
			req.getSession().setAttribute("loggedGezin", gn);
			req.getSession().setAttribute("loggedGezinslidBSN", g.getBSN());
		} else {
			e_msgs = e_msgs + "Er is iets misgegaan tijdens het inloggen!<br>";
		}

		RequestDispatcher rd = null;
		if (logSuccess) {
			req.setAttribute("msgs", "Ingelogd!");
			rd = req.getRequestDispatcher("ingelogd/overzicht.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("msgs", e_msgs);
			rd = req.getRequestDispatcher("index.jsp");
			rd.forward(req, resp);
		}
	}
}
