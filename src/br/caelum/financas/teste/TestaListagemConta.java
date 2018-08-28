package br.caelum.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;

import br.caelum.financas.dao.ContaDao;
import br.caelum.financas.modelo.Conta;
import br.caelum.financas.util.JPAUtil;

public class TestaListagemConta {
	public static void main(String[] args) {
		JPAUtil jpaUtil = new JPAUtil();
		
		EntityManager em = null;
		try {
			em = jpaUtil.getControleFinancasEM();
			
			ContaDao contaDao = new ContaDao(em);
			
			List<Conta> lista = contaDao.lista();
			
			lista.forEach(System.out::println);
		} finally {
			if (em != null) {
				em.close();
			}
			jpaUtil.closeControleFinancasEmf();
		}
	}
}
