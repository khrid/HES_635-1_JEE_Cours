package ch.hevs.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnexionManager {
	private static Connection conn;

	public static Connection getConnexion() {
		if (conn == null) {
			try {
				Class.forName("org.hsqldb.jdbcDriver");
				conn = DriverManager
						.getConnection("jdbc:hsqldb:hsql://localhost/DB");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;

	}
}
