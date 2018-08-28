package br.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.ContaDao;
import br.caelum.financas.modelo.Conta;
import br.caelum.financas.util.JPAUtil;

public class TestaAlteracaoDeLista {
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		
		EntityManager em = null;
		try {
			em = jpaUtil.getControleFinancasEM();
			
			ContaDao contaDao = new ContaDao(em);
			
			em.getTransaction().begin();
			
			List<Conta> lista = contaDao.lista();
			
			lista.forEach(conta -> conta.setTitular("Fulano"));
			
			em.getTransaction().commit();
			
			System.out.println("Contas alteradas com sucesso!");
			lista.forEach(System.out::println);
		} catch (Exception ex) {
			ex.printStackTrace();
			
			if (em != null && em.getTransaction() != null) {
				em.getTransaction().rollback();
			}
		} finally {
			if (em != null) {
				em.close();
			}
			jpaUtil.closeControleFinancasEmf();
		}
	}
}
