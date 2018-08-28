package br.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.ContaDao;
import br.caelum.financas.modelo.Conta;
import br.caelum.financas.util.JPAUtil;

public class TestaRemoveConta {
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		
		EntityManager em = null;
		
		try {
			em = jpaUtil.getControleFinancasEM();
			
			ContaDao contaDao = new ContaDao(em);
			
			em.getTransaction().begin();
			
			Conta conta = contaDao.busca(1);
			contaDao.remove(conta);
			
			em.getTransaction().commit();
			
			System.out.println("Conta removida com sucesso!");
			System.out.println(conta);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			em.getTransaction().rollback();
		} finally {
			if (em != null) {
				em.close();
			}
			jpaUtil.closeControleFinancasEmf();
		}
	}
}
