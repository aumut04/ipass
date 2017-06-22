package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;

public class activiteitDAO extends baseDAO {

	private ArrayList<Activiteit> selectActiviteiten(String query, Gezinslid gl) {
		ArrayList<Activiteit> results = new ArrayList<Activiteit>();
		Activiteit newAct = null;
		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, gl.getBSN());
			ResultSet dbResultSet = stmt.executeQuery();
			while (dbResultSet.next()) {
				int activiteitID = dbResultSet.getInt("activiteitID");
				String activiteitNaam = dbResultSet.getString("activiteitNaam");
				String omschrijving = dbResultSet.getString("omschrijving");
				int BSN = dbResultSet.getInt("bsn");
				String gezinslidNaam = dbResultSet.getString("gezinslidNaam");
				String gezinslidWachtwoord = dbResultSet.getString("wachtwoord");
				Gezinslid gezinslid = new Gezinslid(BSN, gezinslidNaam, gezinslidWachtwoord);
				newAct = new Activiteit(activiteitID, activiteitNaam, omschrijving, gl);
				results.add(newAct);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public Gezinslid getAlleActiviteitenGezinslid(Gezinslid gl) {
		gl.setActiviteiten(selectActiviteiten(
				"SELECT a.activiteitID, a.activiteitNaam, a.omschrijving, gl.bsn, gl.gezinslidNaam, gl.wachtwoord  FROM activiteit a, gezinslid gl, gezinslidactiviteit gla WHERE gla.fk_bsn = gl.bsn and gla.fk_activiteitID = a.activiteitID and gl.bsn = ?;",
				gl.getBSN()));
		return gl;
	}

	@SuppressWarnings("unused")
	private ArrayList<Activiteit> selectActiviteiten(String query, int bsn) {
		ArrayList<Activiteit> results = new ArrayList<Activiteit>();
		Activiteit newAct = null;
		try (Connection con = super.getConnection()) {
			PreparedStatement stmt = con.prepareStatement(query);
			stmt.setInt(1, bsn);
			ResultSet dbResultSet = stmt.executeQuery();
			while (dbResultSet.next()) {
				int activiteitID = dbResultSet.getInt("activiteitID");
				String activiteitNaam = dbResultSet.getString("activiteitNaam");
				String omschrijving = dbResultSet.getString("omschrijving");
				int BSN = dbResultSet.getInt("bsn");
				String gezinslidNaam = dbResultSet.getString("gezinslidNaam");
				String gezinslidWachtwoord = dbResultSet.getString("wachtwoord");
				Gezinslid gl = new Gezinslid(bsn, gezinslidNaam, gezinslidWachtwoord);
				newAct = new Activiteit(activiteitID, activiteitNaam, omschrijving, gl);
				results.add(newAct);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public ArrayList<Activiteit> getAlleActiviteitenGezinslid(int bsn) {
		return selectActiviteiten(
				"SELECT a.activiteitID, a.activiteitNaam, a.omschrijving, gl.bsn, gl.gezinslidNaam, gl.wachtwoord FROM activiteit a, gezinslid gl, gezinslidactiviteit gla WHERE gla.fk_bsn = gl.bsn and gla.fk_activiteitID = a.activiteitID and gl.bsn = ?",
				bsn);
	}

	public void voegActToe(Activiteit a) {
		try (Connection con = super.getConnection()) {
			String updateString = "INSERT INTO activiteit (activiteitNaam, omschrijving) VALUES (?, ?)";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setString(1, a.getActiviteitNaam());
			stmt.setString(2, a.getOmschrijving());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void wijzigActiviteit(Activiteit a, Gezinslid gl, String activiteitnaam) {
		try (Connection con = super.getConnection()) {
			String updateString = "UPDATE Activiteit SET activiteitnaam = ?, omschrijving = ? WHERE activiteitnaam = ?";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setString(1, a.getActiviteitNaam());
			stmt.setString(2, a.getOmschrijving());
			stmt.setString(3, activiteitnaam);
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void verwijderAct(String activiteitNaam) {
		try (Connection con = super.getConnection()) {
			String updateString = "DELETE FROM activiteit WHERE activiteitNaam = ?";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setString(1, activiteitNaam);
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

}
