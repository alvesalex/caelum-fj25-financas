package br.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.ContaDao;
import br.caelum.financas.modelo.Conta;
import br.caelum.financas.util.JPAUtil;

public class TestaAlteraConta {
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		
		EntityManager em = null;
		
		try {
			em = jpaUtil.getControleFinancasEM();
			
			ContaDao contaDao = new ContaDao(em);
			
			em.getTransaction().begin();
			
			Conta conta = contaDao.busca(2);
			conta.setTitular("Joãozinho");
			
			em.getTransaction().commit();
			
			System.out.println("Conta alterada com sucesso!");
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
