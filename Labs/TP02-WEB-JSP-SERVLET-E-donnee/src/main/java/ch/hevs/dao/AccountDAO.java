package ch.hevs.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.dao.util.ConnexionManager;
import ch.hevs.exception.BankException;


public class AccountDAO {

	public static Account search(long id) throws BankException {
		Account result = null;
		try {
			Connection connexion = ConnexionManager.getConnexion();

			PreparedStatement pSt = connexion
					.prepareStatement("SELECT * FROM COMPTE WHERE ID=?");
			pSt.setLong(1, id);
			ResultSet rs = pSt.executeQuery();
			rs.next();
			result = new Account();
			result.setId(id);
			result.setNumber(rs.getString("NUMERO"));
			result.setSaldo(rs.getLong("SOLDE"));

			// We do not map the relation with the client => this is a 
			// limitation of the example

		} catch (Exception e) {
			throw new BankException(e);
		}
		return result;

	}

	public static void saveOrUpdate(Account c) throws BankException {
		try {
			Connection connexion = ConnexionManager.getConnexion();

			// Remark:
			// The column id has been declared as 'identity' in the database
			// It will thus be auto-incremented			
			PreparedStatement pSt;

			Account account = search(c.getId());

			if (account == null) {
				// STORES A NEW ACCOUNT
				pSt = connexion
						.prepareStatement("INSERT INTO COMPTE (ID, NUMER0, SOLDE, FK_CLIENT) VALUES (NULL, ? , ? , ?)");
				pSt.setString(1, c.getNumber());
				pSt.setLong(2, c.getSaldo());
				pSt.setLong(3, c.getOwner().getId());
				pSt.executeUpdate();

				// get the primary key that has just been given to the record by the database
				// Basic implementation.
				pSt = connexion
						.prepareStatement("SELECT ID FROM COMPTE ID WHERE NUMERO=? AND SOLDE=? AND FK_CLIENT=?");
				pSt.setString(1, c.getNumber());
				pSt.setLong(2, c.getSaldo());
				pSt.setLong(3, c.getOwner().getId());

				ResultSet rs = pSt.executeQuery();

				if (!rs.next()) {
					throw new BankException("impossible to get the Id !");
				} else {
					c.setId(rs.getLong("ID"));
				}
				System.out.println("Account inserted in the database with the Id"
						+ c.getId());
			} else {
				// Modification of an existing account
				pSt = connexion
						.prepareStatement(" UPDATE COMPTE SET NUMERO = ? , SOLDE = ? WHERE ID=?");
				pSt.setString(1, c.getNumber());
				pSt.setLong(2, c.getSaldo());
				pSt.setLong(3, c.getId());
				pSt.executeUpdate();
				System.out.println("Modified Account");

			}
		} catch (SQLException e) {
			throw new BankException(e);
		}

	}

	public static List<Account> getClientAccount(String name)
			throws BankException {
		List<Account> result = new ArrayList<Account>();

		try {
			Connection connexion = ConnexionManager.getConnexion();

			PreparedStatement pSt = connexion
					.prepareStatement("SELECT * FROM CLIENT WHERE NOM=?");
			pSt.setString(1, name);
			ResultSet rs = pSt.executeQuery();
			rs.next();
			String id = rs.getString("ID");

			pSt = connexion
					.prepareStatement("SELECT * FROM COMPTE WHERE FK_CLIENT=?");
			pSt.setString(1, id);

			rs = pSt.executeQuery();
			while (rs.next()) {
				Account c = new Account();
				c.setId(rs.getLong("ID"));
				c.setNumber(rs.getString("ID"));
				// Think about the mapping of the relation with the owner
				c.setSaldo(rs.getLong("SOLDE"));
				result.add(c);
			}

		} catch (Exception e) {
			throw new BankException(e);
		}
		return result;
	}
	
	public static List<Account> getAccounts(Client client) throws BankException {
		List<Account> result = new ArrayList<Account>();
		Connection connexion = null;
		try {
			connexion = ConnexionManager.getConnexion();
			Statement St = connexion.createStatement();
			ResultSet rs = St
					.executeQuery("SELECT * FROM COMPTE WHERE FK_CLIENT='"
							+ client.getId() + "'");

			Account c;
			while (rs.next()) {
				c = new Account();
				c.setId(rs.getLong("ID"));
				c.setNumber(rs.getString("NUMERO"));
				c.setSaldo(rs.getLong("SOLDE"));
				c.setOwner(client);
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

}
