package br.caelum.financas.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPAUtil {
	private static EntityManagerFactory controleFinancasEmf = Persistence.createEntityManagerFactory("controlefinancas");
	
	public EntityManager getControleFinancasEM() {
		return controleFinancasEmf.createEntityManager();
	}
	
	public void closeControleFinancasEmf() {
		if (controleFinancasEmf != null) {
			controleFinancasEmf.close();
		}
	}
}
