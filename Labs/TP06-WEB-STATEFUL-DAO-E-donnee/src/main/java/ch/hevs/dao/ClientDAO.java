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
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();

			// remark:
			// the column id is declared as 'identity' in the dataase
			// it will be auto-incremented

			PreparedStatement pSt = connexion
					.prepareStatement("INSERT INTO CLIENT (ID, NOM, PRENOM) VALUES (NULL, ? , ? , ?)");
			pSt.setString(1, c.getLastname());
			pSt.setString(2, c.getFirstname());
			pSt.executeUpdate();

			pSt = connexion
					.prepareStatement("SELECT ID FROM CLIENT ID WHERE NOM=? AND PRENOM=? AND DESCRIPTION=?");
			pSt.setString(1, c.getLastname());
			pSt.setString(2, c.getFirstname());

			ResultSet rs = pSt.executeQuery();

			if (!rs.next()) {
				throw new BankException("impossible to get the id  !");
			} else {
				c.setId(rs.getLong("ID"));
			}
			System.out.println("client inserted in the database with id  "
					+ c.getId());

		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static List<Client> getClients() throws BankException {
		List<Client> result = new ArrayList<Client>();
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();
			Statement St = connexion.createStatement();

			ResultSet rs = St.executeQuery("SELECT * FROM CLIENT");

			Client c;
			while (rs.next()) {
				c = new Client();
				c.setId(rs.getLong("ID"));
				c.setLastname(rs.getString("NOM"));
				c.setFirstname(rs.getString("PRENOM"));
				c.setAccounts(AccountDAO.getAccounts(c));
				result.add(c);
			}
		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return result;

	}

	public static void delete(Client c) throws BankException {
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();
			PreparedStatement pSt = connexion
					.prepareStatement("DELETE FROM CLIENT WHERE ID=?");
			pSt.setLong(1, c.getId());
			pSt.executeUpdate();
			System.out.println("Client " + c.getId() + " deleted");

			pSt = connexion
					.prepareStatement("DELETE FROM COMPTE WHERE CLIENT_FK=?");
			pSt.setLong(1, c.getId());
			pSt.executeUpdate();
			System.out.println("His accounts" + c.getId() + " also !");

		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void modify(Client c) throws BankException {
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();
			PreparedStatement pSt = connexion
					.prepareStatement("UPDATE CLIENT SET NOM=?, PRENOM=?,DESCRIPTION=? WHERE ID=?");
			pSt.setString(1, c.getLastname());
			pSt.setString(2, c.getFirstname());
			pSt.setLong(4, c.getId());

			pSt.executeUpdate();
			System.out.println("Client " + c.getId() + " modifié");
			System.out
					.println("The persistence system doesn't check if associated accounts to the client have been modified");
		} catch (SQLException e) {
			throw new BankException(e);
		} finally {
			try {
				connexion.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
