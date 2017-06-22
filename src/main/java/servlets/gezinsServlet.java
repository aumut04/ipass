package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;
import services.*;

@SuppressWarnings("serial")
public class gezinsServlet extends HttpServlet {
	gezinslidService service = ServiceProvider.glService();

	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int BSN = 0;
		try {
			BSN = Integer.parseInt(req.getParameter("bsn"));
		} catch (NumberFormatException e) {
		}
		int gID = 0;
		try {
			gID = Integer.parseInt(req.getParameter("gID"));
		} catch (NumberFormatException e) {
		}
		String naamNieuweGezinslid = req.getParameter("naamNieuweGezinslid");
		String wachtwoord = req.getParameter("wachtwoord");
		String pType = req.getParameter("pType");
		Object loggedInUserO = req.getSession().getAttribute("loggedGebruiker");
		String e_msgs = "";
		Gezinslid g = (Gezinslid) loggedInUserO;
		boolean wijzigingSuccess = false;
		Gezin gezin = new Gezin();

		if (pType.equals("gezinslidVerwijderen")) {
			Gezinslid gl = new Gezinslid(BSN, naamNieuweGezinslid, wachtwoord);
			service.verwijderGezinslid(gl);
			gezin.setGezinsLeden(service.getAlleGezinsleden());
			req.getSession().setAttribute("loggedGebruiker", g);
			wijzigingSuccess = true;
			e_msgs = e_msgs + "Gezinslid verwijderd<br>";

		} else if (pType.equals("gezinslidOpslaan")) {
			if (naamNieuweGezinslid.isEmpty() || naamNieuweGezinslid == null) {
				e_msgs = e_msgs + "Naam ongeldig/niet ingevuld!<br>";
			} else {
				Gezinslid gl = new Gezinslid(BSN, naamNieuweGezinslid, wachtwoord);
				service.wijzigGezinslid(gl);
				gezin.setGezinsLeden(service.getAlleGezinsleden());
				req.getSession().setAttribute("loggedGebruiker", g);
				wijzigingSuccess = true;
				e_msgs = e_msgs + "Wijziging opgeslagen<br>";
			}
		} else if (pType.equals("gezinslidToevoegen")) {
			if (naamNieuweGezinslid.isEmpty() || naamNieuweGezinslid == null) {
				e_msgs = e_msgs + "Naam ongeldig/niet ingevuld!<br>";
			} else {
				Gezinslid gl = new Gezinslid(BSN, naamNieuweGezinslid, wachtwoord);
				gezin = service.getGezin(gID);
				service.voegGezinslidToe(gl, gezin);;
				gezin.setGezinsLeden(service.getAlleGezinsleden());;
				req.getSession().setAttribute("loggedGebruiker", g);
				wijzigingSuccess = true;
				e_msgs = e_msgs + "Gezinslid toegevoegd<br>";
			}
		}

		RequestDispatcher rd = null;
		if (wijzigingSuccess) {
			req.setAttribute("msgs", e_msgs);
			rd = req.getRequestDispatcher("/secure/gezinsLeden.jsp");
			rd.forward(req, resp);
		} else {
			req.setAttribute("msgs", e_msgs);
			rd = req.getRequestDispatcher("/secure/gezinsLeden.jsp");
			rd.forward(req, resp);
		}
	}
}
