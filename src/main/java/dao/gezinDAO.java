package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.*;

public class gezinDAO extends baseDAO {
	private List<Gezin> gezinnen(String query){
		List<Gezin> results = new ArrayList<Gezin>();
		try (Connection con = super.getConnection()) {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			while (rs.next()) {
				int gezinID = rs.getInt("gezinID");
				String gezinsNaam = rs.getString("gezinsNaam");
				Gezin newGezin = new Gezin(gezinID, gezinsNaam);
				results.add(newGezin);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		return results;
	}
	
	public List<Gezin> getAlleGezinnen() {
		return gezinnen("select gezinID, gezinsNaam from gezin");
	}
	
	public Gezin getGezin(int gezinID) {
		Gezin rs = null;
		for (Gezin g : getAlleGezinnen()){
			if (g.getGezinID() == gezinID) {
				rs = g;
			}
		}
		return rs;
	}

	public List<Gezin> getAlleGezinsleden() {
		return gezinnen("select gezinID, gezinsNaam from gezin");

	}
}
