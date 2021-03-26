package ch.hevs.test;

import junit.framework.TestCase;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;

public class CreateSchemaTest extends TestCase {

	@Test
	public void test() {

		Configuration cfg = new Configuration();
		cfg.addAnnotatedClass(ch.hevs.businessobject.Client.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.InternalAccount.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.ExternalAccount.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Operation.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Agency.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Banker.class);
		cfg.addAnnotatedClass(ch.hevs.businessobject.Address.class);
		cfg.setProperty("hibernate.dialect",
				"org.hibernate.dialect.HSQLDialect");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.driver_class",
				"org.hsqldb.jdbcDriver");
		cfg.setProperty("hibernate.connection.url",
				"jdbc:hsqldb:hsql://localhost/DB");
		cfg.setProperty("hibernate.connection.username", "sa");
		cfg.setProperty("hibernate.connection.password", "");

		new SchemaExport(cfg).setOutputFile("schema.ddl").create(false, true);
	}
}
