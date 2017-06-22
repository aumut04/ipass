package servlets;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.*;

@SuppressWarnings("serial")
public class logoutServlet extends HttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Object loggedInUserO = req.getSession().getAttribute("loggedGezinslid");
		String e_msgs = "";

		boolean loggedOut = false;

		if (loggedInUserO != null) {
			Gezinslid g = (Gezinslid) loggedInUserO;
			e_msgs = "Gezinslid " + g.getGezinslidNaam() + " is succesvol uitgelogd!";
			req.getSession().removeAttribute("loggedGezinslid");
			req.getSession().invalidate();
			loggedOut = true;
		}

		RequestDispatcher rd = null;
		if (loggedOut) {
			req.setAttribute("msgs", e_msgs);
			rd = req.getRequestDispatcher("/index.jsp");
			rd.forward(req, resp);
		}

	}
}
