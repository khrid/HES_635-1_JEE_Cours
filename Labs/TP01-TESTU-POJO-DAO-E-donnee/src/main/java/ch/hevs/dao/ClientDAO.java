package ch.hevs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.businessobject.Client;
import ch.hevs.dao.util.ConnexionManager;
import ch.hevs.exception.BankException;


public class ClientDAO {

	public static void save(Client c) throws BankException {
		try {
			Connection connexion = ConnexionManager.getConnexion();

			// remark:
			// the column id has been declared as 'identity' in the database,
			// thus it will be auto-incremented
			
			PreparedStatement pSt = connexion
					.prepareStatement("INSERT INTO CLIENT (ID, NOM, PRENOM) VALUES (NULL, ? , ? )");
			pSt.setString(1, c.getLastname());
			pSt.setString(2, c.getFirstname());
			pSt.executeUpdate();

			// get back the primary key that has just been given to the record in the database
			pSt = connexion
					.prepareStatement("SELECT ID FROM CLIENT ID WHERE NOM=? AND PRENOM=?");
			pSt.setString(1, c.getLastname());
			pSt.setString(2, c.getFirstname());

			ResultSet rs = pSt.executeQuery();

			if (!rs.next()) {
				throw new BankException("impossible to get the Id !");
			} else {
				c.setId(rs.getLong("ID"));
			}
			System.out.println("Client inserts in the database the id "
					+ c.getId());

		} catch (SQLException e) {
			throw new BankException(e);
		}

	}

	public static List<Client> getClients() throws BankException {
		List<Client> result = new ArrayList<Client>();

		try {
			Connection connexion = ConnexionManager.getConnexion();

			// Extraction
			Statement St = connexion.createStatement();
			ResultSet rs = St.executeQuery("SELECT * FROM CLIENT");

			Client c;
			while (rs.next()) {
				// Mapping
				c = new Client();
				c.setId(rs.getLong("ID"));
				c.setLastname(rs.getString("NOM"));
				c.setFirstname(rs.getString("PRENOM"));
				result.add(c);
			}
		} catch (SQLException e) {
			throw new BankException(e);
		}

		return result;

	}

	public static void delete(Client c) throws BankException {
		try {
			Connection connexion = ConnexionManager.getConnexion();
			PreparedStatement pSt = connexion
					.prepareStatement("DELETE FROM CLIENT WHERE ID=?");
			pSt.setLong(1, c.getId());
			pSt.executeUpdate();
			System.out.println("Client " + c.getId() + " deleted");
		} catch (SQLException e) {
			throw new BankException(e);
		}
	}

	public static void modify(Client c) throws BankException {
		try {
			Connection connexion = ConnexionManager.getConnexion();
			PreparedStatement pSt = connexion
					.prepareStatement("UPDATE CLIENT SET NOM=?, PRENOM=? WHERE ID=?");
			pSt.setString(1, c.getLastname());
			pSt.setString(2, c.getFirstname());
			pSt.setLong(3, c.getId());

			pSt.executeUpdate();
			System.out.println("Client " + c.getId() + " modified");
		} catch (SQLException e) {
			throw new BankException(e);
		}
	}
}
