package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import model.*;

public class gezinslidDAO extends baseDAO {
	
	private ArrayList<Gezinslid> getAlleGezinsledenSQL(String query) {
		ArrayList<Gezinslid> results = new ArrayList<Gezinslid>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet dbResultSet = stmt.executeQuery(query);
			while (dbResultSet.next()) {
				int gId = dbResultSet.getInt("fk_gezinID");
				int bsn = dbResultSet.getInt("bsn");
				String gezinslidNaam = dbResultSet.getString("gezinslidNaam");
				String gezinslidWachtwoord = dbResultSet.getString("wachtwoord");
				Gezinslid newGezinslid = new Gezinslid(gId, bsn, gezinslidNaam, gezinslidWachtwoord);
				results.add(newGezinslid);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}

	public ArrayList<Gezinslid> getAlleGezinsleden() {
		return getAlleGezinsledenSQL("select fk_gezinID, bsn, gezinslidnaam, wachtwoord from gezinslid");
	}

	public ArrayList<Gezinslid> getAlleGezinsledenGezin(int gezinID) {
		return getAlleGezinsledenSQL("select fk_gezinID, bsn, gezinslidnaam, wachtwoord from gezinslid where fk_gezinID ='" + gezinID + "';");
	}

	public void verwijderGezinslid(Gezinslid gl) {
		try (Connection con = super.getConnection()) {
			String updateString = "DELETE FROM Gezinslid WHERE bsn = ?;";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setInt(1, gl.getBSN());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void verwijderGezinslid(int bsn) {
		try (Connection con = super.getConnection()) {
			String updateString = "DELETE FROM Gezinslid WHERE bsn = ?;";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setInt(1, bsn);
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void voegGezinslid(Gezinslid gl, Gezin g) {
		try (Connection con = super.getConnection()) {
			String updateString = "INSERT INTO Gezinslid (bsn, gezinslidnaam, wachtwoord, fk_gezinID) VALUES (?, ?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setInt(1, gl.getBSN());
			stmt.setString(2, gl.getGezinslidNaam());
			stmt.setString(3, gl.getWachtwoord());
			stmt.setInt(4, g.getGezinID());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public void wijzigGezinslid(Gezinslid gl) {
		try (Connection con = super.getConnection()) {
			String updateString = "UPDATE Gezinslid SET gezinslidnaam = ? WHERE bsn = ?";
			PreparedStatement stmt = con.prepareStatement(updateString);
			stmt.setString(1, gl.getGezinslidNaam());
			stmt.setInt(2, gl.getBSN());
			stmt.executeUpdate();
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
	}

	public Gezinslid getGezinslid(int bsn) {
		Gezinslid gl = null;
		for (Gezinslid glid : getAlleGezinsleden()) {
			if (glid.getBSN() == bsn) {
				gl = glid;
			}
		}
		return gl;
	}
}
