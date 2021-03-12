package ch.hevs.dao.util;

import java.sql.Connection;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class ConnexionManager {
	private static DataSource ds;

	public static Connection getConnexion() {
		Connection result = null;

		try {
			if (ds == null) {
				Context ctx = new InitialContext();
				ds = (DataSource) ctx.lookup("java:/labsDS");
			}

			result = ds.getConnection();
		} catch (Exception e) {
			ds = null;
			e.printStackTrace();
		}

		return result;

	}
}