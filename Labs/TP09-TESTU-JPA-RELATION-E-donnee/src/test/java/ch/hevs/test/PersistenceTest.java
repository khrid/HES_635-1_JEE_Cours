package ch.hevs.test;

import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Test;

import ch.hevs.businessobject.Address;
import ch.hevs.businessobject.Agency;
import ch.hevs.businessobject.Banker;
import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.ExternalAccount;
import ch.hevs.businessobject.InternalAccount;
import ch.hevs.businessobject.Operation;

public class PersistenceTest {

	@Test
	public void test() {
		EntityTransaction tx = null;
		try {
			
			EntityManagerFactory emf = Persistence.createEntityManagerFactory("bankPU");
			EntityManager em = emf.createEntityManager();
			tx = em.getTransaction();
			tx.begin();

			/*
			 * InternalAccount c = new InternalAccount("0","madescr");
			 * c.addOperation(new Operation("intitule",19,new Date()));
			 * em.persist(c);
			 */

			Client c1 = new Client("Platini", "Michel", "Ex footballer");
			Address a1 = new Address("75000", "Champs elyse", "Paris");
			c1.setAddress(a1);

			c1.addExternalAccount(new ExternalAccount("010101", "Account A"));
			c1.addExternalAccount(new ExternalAccount("020202", "Account B"));

			InternalAccount ci1 = new InternalAccount("1", "desc1", 1000);
			InternalAccount ci2 = new InternalAccount("2", "desc2", 2000);

			ci1.addOperation(new Operation("Boucher", 10, new Date()));
			ci1.addOperation(new Operation("Charcutier", 12, new Date()));

			ci2.addOperation(new Operation("Plasma", 10000, new Date()));
			ci2.addOperation(new Operation("Ipod", 450, new Date()));
			ci2.addOperation(new Operation("Nounou", 700, new Date()));

			c1.addInternalAccount(ci1);
			c1.addInternalAccount(ci2);

			Client c2 = new Client("Parker", "Tony", "Basketteur");
			Address a2 = new Address("1950", "Av. de France", "Sion");
			c2.setAddress(a2);

			c2.addExternalAccount(new ExternalAccount("88888", "Account of Denis"));
			c2.addExternalAccount(new ExternalAccount("77777", "Account in Switzerland"));

			InternalAccount ci3 = new InternalAccount("3", "desc3", 3000);
			ci3.addOperation(new Operation("Restaurant", 10000, new Date()));
			ci3.addOperation(new Operation("Prada", 1000, new Date()));

			c2.addInternalAccount(ci2); // account shared with Michel platini
			// :-)
			c2.addInternalAccount(ci3);

			Agency a = new Agency();
			a.addClient(c1);
			a.addClient(c2);

			Banker b1 = new Banker("Alexandre", "Suarez", "alex@hevs.ch");
			Banker b2 = new Banker("Marguerite", "Duras", "duras@hevs.ch");

			a.addBanker(b1);
			a.addBanker(b2);

			Address a3 = new Address("01", "Beside lake Leman", "Lausanne");
			a.setAddress(a3);

			em.persist(a);
			tx.commit();

		} catch (Exception e) {
			e.printStackTrace();

			try {
				tx.rollback();
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			}

		}

	}
}
