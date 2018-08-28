package br.caelum.financas.teste;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.ContaDao;
import br.caelum.financas.modelo.Conta;
import br.caelum.financas.util.JPAUtil;

public class TestePesquidaIdConta {
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		
		EntityManager em = null;
		
		try {
			em = jpaUtil.getControleFinancasEM();
			
			ContaDao contaDao = new ContaDao(em);
			
			Conta encontrado = contaDao.busca(1);
			
			System.out.println(encontrado);
		} finally {
			if (em != null) {
				em.close();
			}
			jpaUtil.closeControleFinancasEmf();
		}
	}
}
